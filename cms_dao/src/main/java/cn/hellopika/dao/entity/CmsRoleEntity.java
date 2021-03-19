package cn.hellopika.dao.entity;

import cn.hellopika.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsRoleEntity extends BaseEntity<Integer> {
    private String name;
    private Integer priority;
    private Boolean all;
    private Boolean status;
}
