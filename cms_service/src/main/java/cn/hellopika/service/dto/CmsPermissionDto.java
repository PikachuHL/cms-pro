package cn.hellopika.service.dto;

import cn.hellopika.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsPermissionDto extends BaseDto<Integer> {
    private Integer parentId;
    private Boolean menu;
    private String icon;
    private String name;
    private String action;
    private String url;
    private Integer priority;
}
