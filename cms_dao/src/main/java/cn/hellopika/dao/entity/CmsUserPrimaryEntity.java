package cn.hellopika.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 用户主表的 entity
 */
@Getter
@Setter
public class CmsUserPrimaryEntity {
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer loginCount;
}
