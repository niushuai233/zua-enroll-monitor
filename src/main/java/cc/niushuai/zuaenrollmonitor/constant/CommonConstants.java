package cc.niushuai.zuaenrollmonitor.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 *
 * @author niushuai
 * @date 2021/7/27 16:47:19
 */
public class CommonConstants {

    /**
     * token map
     */
    public static Map<String, String> access_token_map = new HashMap<>();

    /**
     * ACCESS_TOKEN
     */
    public static final String STR_ACCESS_TOKEN = "ACCESS_TOKEN";
    /**
     * EXPIRES
     */
    public static final String STR_EXPIRES = "EXPIRES";
    /**
     * updated
     */
    public static final String STR_UPDATED = "UPDATED";
    /**
     * STR_CORPID
     */
    public static final String STR_CORPID = "CORPID";
    /**
     * STR_SECRET
     */
    public static final String STR_SECRET = "SECRET";

    /**
     * access_token
     */
    public static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=SECRET";

    /**
     * 应用发送消息
     */
    public static final String APP_MSG_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";


}
