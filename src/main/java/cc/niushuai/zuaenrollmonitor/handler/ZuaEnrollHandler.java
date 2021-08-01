package cc.niushuai.zuaenrollmonitor.handler;

import cc.niushuai.zuaenrollmonitor.config.CustomEnv;
import cc.niushuai.zuaenrollmonitor.dao.EnrollLogRepository;
import cc.niushuai.zuaenrollmonitor.entity.EnrollLog;
import cc.niushuai.zuaenrollmonitor.entity.Result;
import cc.niushuai.zuaenrollmonitor.entity.Student;
import cc.niushuai.zuaenrollmonitor.util.KudiDiUtil;
import cc.niushuai.zuaenrollmonitor.util.MessageUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 定时任务处理类
 *
 * @author niushuai
 * @date 2021/7/27 16:45:53
 */
@Slf4j
@Component
public class ZuaEnrollHandler {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private CustomEnv customEnv;

    @Resource
    private MessageUtil messageUtil;

    @Resource
    private EnrollLogRepository enrollLogRepository;


    @Scheduled(fixedDelay = 1000L, fixedRate = 2 * 60 * 60 * 1000L)
    public void testGet() {


        String ems = KudiDiUtil.getEms("9865660291832");
        System.out.println(ems);
    }


    //@Scheduled(cron = "${niushuai233.zua.cron:0 0 0-23/2 * * ?}")
    @Scheduled(initialDelay = 1000L, fixedDelay = 2 * 60 * 60 * 1000L)
    public void research() {

        if (isNight()) {
            log.info("isNight return");
            return;
        }

        Student student = new Student().setSfzh(customEnv.getIdcardNumber()).setKsh(customEnv.getExamNumber());

        try {
            String res = restTemplate.postForObject(customEnv.getRequestUrl(), student, String.class);
            parseHandle(student, res);
        } catch (Exception e) {
            log.error("请求失败: {}", e.getMessage(), e);
            messageUtil.send("请求失败: " + e.getMessage());
        }
    }

    private boolean isNight() {

        if (Boolean.FALSE.toString().equalsIgnoreCase(customEnv.getNight())) {
            return false;
        }

        int currHour = DateUtil.date().getField(DateField.HOUR_OF_DAY);
        if (currHour > 9 && currHour < 21) {
            return false;
        }

        return true;
    }

    private void parseHandle(Student student, String res) {
        StringBuffer buffer = new StringBuffer();

        boolean validate = JSONUtil.isJson(res);
        if (validate) {
            // 成功, 发送消息
            Result result = JSONUtil.toBean(res, Result.class);
            String datas = result.getDatas().toString();
            EnrollLog bean = JSONUtil.toBean(datas, EnrollLog.class);
            bean.setSfzh(student.getSfzh()).setKsh(student.getKsh());

            if (bean.getLqResult().equals("1")) {

                buffer.append("查询成功:").append(System.lineSeparator());
                buffer.append("\t>").append("时间:").append(DateUtil.now()).append(System.lineSeparator());
                buffer.append("\t>").append("年份:").append(bean.getZsnf()).append(System.lineSeparator());
                buffer.append("\t>").append("姓名:").append(bean.getXm()).append(System.lineSeparator());
                buffer.append("\t>").append("学院:").append(bean.getYxmc()).append(System.lineSeparator());
                buffer.append("\t>").append("专业:").append(bean.getZymc()).append(System.lineSeparator());
                buffer.append("\t>").append("EMS:");
                if (StringUtils.hasText(bean.getKddh())) {
                    buffer.append(bean.getKddh()).append(System.lineSeparator());

                    String ems = KudiDiUtil.getEms(bean.getKddh());
                    buffer.append(ems).append(System.lineSeparator());
                } else {
                    buffer.append("暂未查询到快递单号").append(System.lineSeparator());
                }
            } else {
                // 未录取
                buffer.append("查询成功: 未查询到相关信息").append(System.lineSeparator())
                        .append("查询时间: ").append(DateUtil.now()).append(System.lineSeparator())
                        .append("请求参数: ").append(JSONUtil.toJsonStr(student)).append(System.lineSeparator())
                        .append("响应参数: ").append(res).append(System.lineSeparator());
            }

            bean.setId(DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN) + RandomUtil.randomString(5).toUpperCase());
            enrollLogRepository.save(bean);

        } else {
            // 非json数据
            buffer.append("查询时间: ").append(DateUtil.now())
                    .append(System.lineSeparator()).append("非json数据: ").append(res);
        }


        messageUtil.send(buffer.toString());
    }

}
