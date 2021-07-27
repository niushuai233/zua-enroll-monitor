package cc.niushuai.zuaenrollmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 郑航招生系统启动类
 *
 * @author niushuai
 * @date: 2021/7/27 14:58:59
 */
@EnableWebMvc
@EnableScheduling
@EnableConfigurationProperties
@SpringBootApplication
public class ZuaEnrollMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuaEnrollMonitorApplication.class, args);
    }
}
