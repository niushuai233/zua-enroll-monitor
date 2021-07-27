package cc.niushuai.zuaenrollmonitor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取自定义属性类
 *
 * @author niushuai
 * @date: 2021/7/27 14:58:08
 */
@Data
@Component
@ConfigurationProperties(prefix = "custom-env")
public class CustomEnv {

    private String requestUrl;
    private String idcardNumber;
    private String examNumber;
    private String agentid;
    private String cropid;
    private String secret;
    private String toUser;
}