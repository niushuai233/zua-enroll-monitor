package cc.niushuai.zuaenrollmonitor.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Date;

public class CommonUtil {

    public static String randomId() {

        return DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN) + RandomUtil.randomString(6).toUpperCase();
    }
}
