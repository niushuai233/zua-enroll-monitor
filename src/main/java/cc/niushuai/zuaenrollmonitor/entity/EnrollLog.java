package cc.niushuai.zuaenrollmonitor.entity;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 返回结果
 *
 * @author niushuai
 * @date 2021/7/27 17:24:17
 */
@Entity
@Table(name = "t_enroll_log")
@Data
@Accessors(chain = true)
public class EnrollLog {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "sfzh")
    private String sfzh;
    @Column(name = "ksh")
    private String ksh;

    @Column(name = "lqResult")
    private String lqResult;
    @Column(name = "kddh")
    private String kddh;
    @Column(name = "schoolName")
    private String schoolName;
    @Column(name = "xbdm")
    private String xbdm;
    @Column(name = "yxmc")
    private String yxmc;
    @Column(name = "zymc")
    private String zymc;
    @Column(name = "zsnf")
    private String zsnf;
    @Column(name = "xm")
    private String xm;
    @Column(name = "jfdah")
    private String jfdah;
    @Column(name = "createTime")
    private String createTime = DateUtil.now();
}
