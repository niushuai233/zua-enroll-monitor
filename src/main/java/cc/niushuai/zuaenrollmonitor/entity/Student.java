package cc.niushuai.zuaenrollmonitor.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 请求实体类
 *
 * @author niushuai
 * @date 2021/7/27 16:55:35
 */
@Data
@Accessors(chain = true)
public class Student {

    private String sfzh;
    private String ksh;
    private String queryType = "enroll";
}
