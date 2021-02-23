package cn.hellopika.dao.entity;

import cn.hellopika.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户附表的 entity
 */
@Getter
@Setter
public class CmsUserEntity extends BaseEntity<Integer> {
    private String username;
    private Integer status;
    private Boolean admin;
    private String lastLoginIp;
    private String sessionId;
    /**
     * 超级管理员
     */
    private Boolean administrator;
}
