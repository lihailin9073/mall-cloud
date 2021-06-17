package com.wzliulan.mall.cloud.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求头信息解析工具
 */
public class RequestUtil {

//    public static String[] extractAndDecodeHeader(String header) throws IOException {
//        // `Basic ` 后面开始截取 clientId:clientSecret
//        byte[] base64Token = header.trim().substring(6).getBytes(StandardCharsets.UTF_8);
//
//        byte[] decoded;
//        try {
//            decoded = Base64.getDecoder().decode(base64Token);
//        } catch (IllegalArgumentException var8) {
//            throw new RuntimeException("请求头解析失败：" + header);
//        }
//
//        String token = new String(decoded, "UTF-8");
//        int delim = token.indexOf(":");
//        if (delim == -1) {
//            throw new RuntimeException("请求头无效：" + token);
//        } else {
//            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
//        }
//    }

    /**
     * 请求头认证信息解析方法
     * @param header 请求头认证信息
     * @return 返回包含了client_id、client_secret两个key值的map集合
     * @throws IOException
     */
    public static Map<String, String> parseAuthHeaderInfo(String header) throws IOException {
        // `Basic ` 后面开始截取 clientId:clientSecret
        byte[] base64Token = header.trim().substring(6).getBytes(StandardCharsets.UTF_8);

        byte[] decoded = null;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException var8) {
            throw new RuntimeException("请求头解析失败：" + header);
        }

        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new RuntimeException("请求头无效：" + token);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("client_id", token.substring(0, delim));
            map.put("client_secret", token.substring(delim + 1));
            return map;
        }
    }
}
