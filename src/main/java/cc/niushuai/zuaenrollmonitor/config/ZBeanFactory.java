package cc.niushuai.zuaenrollmonitor.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 *
 *  <pre>
 *   ApplicationContext会自动检查是否在定义文件中有实现了BeanPostProcessor接口的类，
 *   如果有的话，Spring容器会在每个Bean(其他的Bean)被初始化之前和初始化之后，
 *   分别调用实现了BeanPostProcessor接口的类的
 *   postProcessAfterInitialization() 和 postProcessBeforeInitialization()方法
 *  </pre>
 *  之所以用z开头是因为 初始化有顺序
 * @author niushuai
 * @date 2021/7/27 14:42:55
 */
@Slf4j
@Configuration
public class ZBeanFactory implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("对象---" + beanName + "---初始化开始");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("对象---" + beanName + "---初始化成功");
        return bean;
    }
}
