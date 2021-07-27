package cc.niushuai.zuaenrollmonitor.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 实例化一些bean
 *
 * @author niushuai
 * @date 2021/7/27 16:47:53
 */
@Slf4j
@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
