package cc.niushuai.zuaenrollmonitor.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 启动类
 *
 * @author niushuai
 * @date 2021/7/27 14:59:43
 */
@Slf4j
@Component
@Order(value = 1)
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {

    @Resource
    private CustomEnv customEnv;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("customEnv: {}", customEnv);
    }
}
