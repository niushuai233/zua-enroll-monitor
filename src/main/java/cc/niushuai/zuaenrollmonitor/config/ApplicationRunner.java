package cc.niushuai.zuaenrollmonitor.config;

import cn.hutool.core.date.DateTime;
import cn.hutool.cron.pattern.CronPattern;
import cn.hutool.cron.pattern.CronPatternUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

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
    public void run(ApplicationArguments args) {
        log.info("customEnv: {}", customEnv);

        if (!CronExpression.isValidExpression(customEnv.getCron())) {
            throw new RuntimeException("未知的cron表达式");
        }

        Date date = CronPatternUtil.nextDateAfter(new CronPattern(customEnv.getCron()), new Date(), true);

        log.info("next exec date: {}", DateTime.of(date));
    }
}
