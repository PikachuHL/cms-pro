package cn.hellopika.dao.entity;

import cn.hellopika.core.foundation.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsPermissionEntity extends BaseEntity<Integer> {
    private Integer parentId;
    private Boolean menu;
    private String icon;
    private String name;
    private String action;
}
