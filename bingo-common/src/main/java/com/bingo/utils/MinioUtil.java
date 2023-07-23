package com.bingo.utils;

import io.minio.*;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 徐志斌
 * @Date: 2023/7/23 18:29
 * @Version 1.0
 * @Description: MinioUtil
 */
@Component
public class MinioUtil {
    @Autowired
    private MinioClient minioClient;

    /**
     * 判断bucket是否存在（不存在则创建）
     */
    public void existBucket(String name) throws Exception {
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(name).build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(name).build());
        }
    }

    /**
     * 创建 bucket
     */
    public void createBucket(String bucketName) throws Exception {
        minioClient.makeBucket(MakeBucketArgs.builder()
                .bucket(bucketName)
                .build());
    }

    /**
     * 删除 bucket
     */
    public void deleteBucket(String bucketName) throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder()
                .bucket(bucketName)
                .build());
    }

    /**
     * 上传文件
     */
    public List<String> upload(MultipartFile[] multipartFile, String bucketName) {
        List<String> names = new ArrayList<>(multipartFile.length);
        for (MultipartFile file : multipartFile) {
            String fileName = file.getOriginalFilename();
            String[] split = fileName.split("\\.");
            if (split.length > 1) {
                fileName = split[0] + "_" + System.currentTimeMillis() + "." + split[1];
            } else {
                fileName = fileName + System.currentTimeMillis();
            }
            InputStream in = null;
            try {
                in = file.getInputStream();
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(in, in.available(), -1)
                        .contentType(file.getContentType())
                        .build()
                );
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            names.add(fileName);
        }
        return names;
    }

    /**
     * 下载文件
     */
    public ResponseEntity<byte[]> download(String bucketName, String fileName) {
        ResponseEntity<byte[]> responseEntity = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
            out = new ByteArrayOutputStream();
            IOUtils.copy(in, out);
            //封装返回值
            byte[] bytes = out.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            try {
                headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            headers.setContentLength(bytes.length);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setAccessControlExposeHeaders(Arrays.asList("*"));
            responseEntity = new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;
    }

    /**
     * 查看文件对象
     */
    public List<Item> listObjects(String bucketName) throws Exception {
        Iterable<Result<Item>> results =
                minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
        List<Item> objectItems = new ArrayList<>();
        for (Result<Item> result : results) {
            Item item = result.get();
            objectItems.add(item);
        }
        return objectItems;
    }

    /**
     * 批量删除文件对象
     */
    public void removeObjects(String bucketName, List<String> objList) {
        List<DeleteObject> objNames = objList.stream().map(item -> new DeleteObject(item)).collect(Collectors.toList());
        minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(bucketName).objects(objNames).build());
    }
}
