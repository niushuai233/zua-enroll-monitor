package cc.niushuai.zuaenrollmonitor.util;

import cc.niushuai.zuaenrollmonitor.config.CustomEnv;
import cc.niushuai.zuaenrollmonitor.constant.CommonConstants;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static cc.niushuai.zuaenrollmonitor.constant.CommonConstants.*;

/**
 * TODO
 *
 * @author niushuai
 * @date 2021/7/27 17:42:23
 */
@Slf4j
@Component
public class MessageUtil {

    @Resource
    private CustomEnv customEnv;

    @Resource
    private RestTemplate restTemplate;

    public void send(String message) {

        message = rebuildStr(message);

        refreshToken();

        try {
            String appMsgUrl = APP_MSG_URL.replace(STR_ACCESS_TOKEN, access_token_map.get(STR_ACCESS_TOKEN));

            String post = restTemplate.postForObject(appMsgUrl, message.getBytes(StandardCharsets.UTF_8), String.class);

            log.info("url: {}", appMsgUrl);
            log.info("params: {}", message);
            log.info("result: {}", post);
            log.info("--------------------------------------------------------------------");
        } catch (Exception e) {
            log.error("发消息失败: {}", e.getMessage(), e);
        }
    }

    private String rebuildStr(String message) {

        Map<String, Object> map = new HashMap<>();
        map.put("touser", customEnv.getToUser());
        map.put("agentid", customEnv.getAgentid());
        map.put("msgtype", "text");

        Map<String, String> tmpMap = new HashMap<>();
        tmpMap.put("content", message);

        map.put("text", tmpMap);

        return JSONUtil.toJsonStr(map);
    }

    private void refreshToken() {
        String token = access_token_map.get(STR_ACCESS_TOKEN);
        String expires = access_token_map.get(STR_EXPIRES);

        if (StrUtil.isEmpty(token) || StrUtil.isEmpty(expires) || timeout(expires)) {
            log.info("token不存在或已过期: {}, {}", expires, token);
            resetToken();
        }
    }

    private boolean timeout(String expires) {
        DateTime expireDateTime = DateUtil.parseDateTime(expires);
        return expireDateTime.isBeforeOrEquals(DateUtil.date());
    }

    private void resetToken() {

        String url = ACCESS_TOKEN_URL.replace(STR_CORPID, customEnv.getCropid()).replace(STR_SECRET, customEnv.getSecret());

        String getRes = restTemplate.getForObject(url, String.class);

        Map map = JSONUtil.toBean(getRes, Map.class);

        String access_token = (String) map.getOrDefault(STR_ACCESS_TOKEN.toLowerCase(), "");

        access_token_map.put(STR_ACCESS_TOKEN, access_token);
        access_token_map.put(STR_UPDATED, DateUtil.now());
        String expires = DateUtil.formatDateTime(DateUtil.offsetMinute(DateUtil.offsetHour(new Date(), 2), -15));
        access_token_map.put(STR_EXPIRES, expires);
        log.info("refresh access token str: {}", access_token);
        log.info("refresh access token expires: {}", expires);
    }

}
