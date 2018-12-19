package com.codelinn.blog.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.druid.util.LRUCache;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * Class Name : org.lynn.springbootstarter.common.util
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/12/19 22:11
 */
@Slf4j
public class IpUtils {

    private static LRUCache<String, String> ipCountryCache = new LRUCache<>(500);

    private static String CN = "CN";

    static {
        ipCountryCache.put("127.0.0.1", "CN");
        ipCountryCache.put("0:0:0:0:0:0:0:1", "CN");
    }

    public static boolean isCnIp(String ip) {
        if (StrUtil.isBlank(ipCountryCache.get(ip))) {
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("ip", ip);
            try {
                String result = HttpUtil.get("http://ip.taobao.com/service/getIpInfo.php", paramMap);
                String countryId = JSONObject.parseObject(result).getJSONObject("data").getString("country_id");
                ipCountryCache.put(ip, countryId);
                if (CN.equals(countryId)) {
                    return true;
                } else {
                    log.info("{} ip reject", ip);
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            return CN.equals(ipCountryCache.get(ip));
        }
    }

}
