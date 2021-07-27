package cc.niushuai.zuaenrollmonitor.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "t_access_token")
@Accessors(chain = true)
public class AccessToken {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "token")
    private String token;

    @Column(name = "createTime")
    private String createTime;

    @Column(name = "expireTime")
    private String expireTime;
}
