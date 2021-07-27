package cc.niushuai.zuaenrollmonitor.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 返回结果
 *
 * @author niushuai
 * @date 2021/7/27 17:24:17
 */
@Data
@Accessors(chain = true)
public class ResBean {

    private String lqResult;
    private String kddh;
    private String schoolName;
    private String xbdm;
    private String yxmc;
    private String zymc;
    private String zsnf;
    private String xm;
    private String jfdah;
}
