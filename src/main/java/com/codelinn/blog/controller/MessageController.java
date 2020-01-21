package com.codelinn.blog.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codelinn.blog.common.ResultEntity;
import com.codelinn.blog.model.Message;
import com.codelinn.blog.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Class Name : com.codelinn.blog.controller
 * Description :
 *
 * @author : cailinfeng
 * Date : 2018/11/12 12:27 PM
 */
@RestController()
@Slf4j
@RequestMapping("/message")
public class MessageController{

    @Autowired
    private MessageService messageService;

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private static final String API_KEY = "DbXk8h3KSVveLOvR09OhChjIQTuI7eOTp6QM7HeifynyxzJesepKtIxUMPjy0mkW";

    private static final String SECRET = "my04cL1P4IQjFWLq1axnWtu8W0XzgRexNwcYpIbSRcwV1ftulL74l6oa73Vt9vJO";

    private static BigDecimal latestFree = BigDecimal.ZERO;

    public static void main(String[] args) {
        executorService.scheduleAtFixedRate(() -> {
            Long currentMillis = System.currentTimeMillis();
            String sign = SecureUtil.hmac(HmacAlgorithm.HmacSHA256, SECRET).digestHex("recvWindow=5000&timestamp=" + currentMillis);
            HttpResponse response = HttpUtil.createGet("https://api.binance.com/api/v3/account").body("recvWindow=5000&timestamp=" + currentMillis + "&signature=" + sign).
                    header("X-MBX-APIKEY", API_KEY).execute();
            String responseBody = response.body();
            JSONArray array = JSONObject.parseObject(responseBody).getJSONArray("balances");
            if (array != null && array.size() > 0) {
                Object bnbObj = array.stream().filter(item -> JSONObject.parseObject(item.toString()).getString("asset").equalsIgnoreCase("BNB")).collect(Collectors.toList()).get(0);
                String bnbFree = JSONObject.parseObject(bnbObj.toString()).getString("free");
                log.info("bnb free: {}", bnbFree);
                if (new BigDecimal(bnbFree).compareTo(latestFree) != 0) {
                    String url = "https://sc.ftqq.com/SCU36514T84104f04eeffb86aa9baa794c763d8b15c7e3f57cf400.send";
                    Map<String, Object> param = new HashMap<>();
                    param.put("text", DateUtil.format(new Date(), "HH时mm分 ") + "BNB持仓变动，之前持仓：【" + latestFree + "】，当前持仓：【" + bnbFree + "】");
                    param.put("desp", "持仓变动");
                    latestFree = new BigDecimal(bnbFree);
                    String result = HttpUtil.post(url, param);
                }
            }
        }, 0, 6, TimeUnit.MINUTES);
    }

    @PostMapping(value = "/leavingMessage", produces = "application/json")
    public ResultEntity leavingMessage(@RequestBody Message message) {
        messageService.insert(message);
        Map<String, Object> param = new HashMap<>();
        param.put("text", "codelinn站内信--来自（" + message.getSender() + "）");
        param.put("desp", message.getMessage());
        try {
            String url = "https://sc.ftqq.com/SCU36514T84104f04eeffb86aa9baa794c763d8b15c7e3f57cf400.send";
            String result = HttpUtil.post(url, param);
            log.info("serverchan message url {}, pushed result {}", url, result);
        } catch (Exception e) {
            log.warn("serverchan sendmessage error, e {}", e);
        }
        return ResultEntity.success("success");
    }


}
