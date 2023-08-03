package com.bingo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @Author 徐志斌
 * @Date: 2023/8/3 22:01
 * @Version 1.0
 * @Description: 根据 HttpServletRequest 获取 ip 地址
 */
@Slf4j
public class IpUtil {
    /**
     * 获取ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("X-Forwarded-For");
            if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
                // 多次反向代理后会有多个ip值，第一个ip才是真实ip
                if (ipAddress.indexOf(",") != -1) {
                    ipAddress = ipAddress.split(",")[0];
                }
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.error("IPUtils ERROR ", e);
        }
        return ipAddress;
    }

    /**
     * 根据ip获取属地
     */
    public static String getCityInfo(String ip) throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("ip2region.db");
        Resource resource = resources[0];
        InputStream is = resource.getInputStream();
        File target = new File("ip2region.db");
        FileUtils.copyInputStreamToFile(is, target);
        is.close();
        if (StringUtils.isEmpty(String.valueOf(target))) {
            log.error("Error: Invalid ip2region.db file");
            return null;
        }
        DbConfig config = new DbConfig();
        DbSearcher searcher = new DbSearcher(config, String.valueOf(target));

        // B-tree, B树搜索（更快）
        int algorithm = DbSearcher.BTREE_ALGORITHM;
        try {
            Method method;
            method = searcher.getClass().getMethod("btreeSearch", String.class);
            DataBlock dataBlock;
            if (!Util.isIpAddress(ip)) {
                log.error("Error: Invalid ip address");
            }
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            String ipInfo = dataBlock.getRegion();
            if (!StringUtils.isEmpty(ipInfo)) {
                ipInfo = ipInfo.replace("|0", "");
                ipInfo = ipInfo.replace("0|", "");
            }
            return ipInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}