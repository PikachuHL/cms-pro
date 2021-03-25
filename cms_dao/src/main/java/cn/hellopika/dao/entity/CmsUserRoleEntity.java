package cn.hellopika.dao.entity;

import cn.hellopika.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsUserRoleEntity extends BaseEntity<Integer> {
    private Integer UserId;
    private Integer RoleId;
}
