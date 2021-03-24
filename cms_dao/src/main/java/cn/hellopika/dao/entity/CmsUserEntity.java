package cn.hellopika.dao.entity;

import cn.hellopika.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 用户附表的 entity
 */
@Getter
@Setter
public class CmsUserEntity extends BaseEntity<Integer> {
    private String username;
    private Boolean status;
    private String email;
    private String password;
    private String salt;
    /**
     * 超级管理员
     */
    private Boolean administrator;

    private LocalDateTime registerTime;

}
