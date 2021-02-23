package cn.hellopika.dao.entity;

import cn.hellopika.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户主表的 entity
 */
@Getter
@Setter
public class CmsUserPrimaryEntity extends BaseEntity<Integer> {
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer loginCount;
}
