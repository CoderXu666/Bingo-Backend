package com.bingo.utils;

import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author 徐志斌
 * @Date: 2023/8/6 11:11
 * @Version 1.0
 * @Description: ip2region：https://github.com/lionsoul2014/ip2region
 */
public class AddressUtil {
    /**
     * 根据 IP 查询登录来源
     */
    public static Map<String, Object> getCityInfo(String ip) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        String dbPath = Objects.requireNonNull(AddressUtil.class.getResource("/ip2region/ip2region.xdb")).getPath();
        Searcher searcher = Searcher.newWithFileOnly(dbPath);
        String cityInfo = searcher.searchByStr(ip);
        if (StringUtils.isNotEmpty(cityInfo)) {
            String[] infoArr = cityInfo.split("\\|");
            String country = infoArr[0];
            String province = infoArr[2];
            String city = infoArr[3];
            resultMap.put("country", country);
            resultMap.put("province", province);
            resultMap.put("city", city);
            return resultMap;
        } else {
            return null;
        }
    }
}

