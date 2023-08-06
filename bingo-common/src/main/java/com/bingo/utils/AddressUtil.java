package com.bingo.utils;

import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.File;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * @Author 徐志斌
 * @Date: 2023/8/6 11:11
 * @Version 1.0
 * @Description: ip2region：https://github.com/lionsoul2014/ip2region
 */
public class AddressUtil {

    /**
     * 当前记录地址的本地DB
     */
    private static final String TEMP_FILE_DIR = "/home/admin/app/";

    /**
     * 根据IP地址查询登录来源
     */
    public static String getCityInfo(String ip) {
        try {
            String dbPath = Objects.requireNonNull(AddressUtil.class.getResource("/ip2region/ip2region.xdb")).getPath();
            File file = new File(dbPath);
            //如果当前文件不存在，则从缓存中复制一份
            if (!file.exists()) {
                dbPath = TEMP_FILE_DIR + "ip.db";
                System.out.println(MessageFormat.format("当前目录为:[{0}]", dbPath));
                file = new File(dbPath);
                FileUtils.copyInputStreamToFile(Objects.requireNonNull(AddressUtil.class.getClassLoader().getResourceAsStream("classpath:ip2region/ip2region.xdb")), file);
            }
            //创建查询对象
            Searcher searcher = Searcher.newWithFileOnly(dbPath);
            //开始查询
            return searcher.searchByStr(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //默认返回空字符串
        return "";
    }
}

