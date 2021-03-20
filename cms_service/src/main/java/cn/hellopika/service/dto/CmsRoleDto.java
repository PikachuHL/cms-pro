package cn.hellopika.service.dto;

import cn.hellopika.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CmsRoleDto extends BaseDto<Integer> {
    private String name;
    private Integer priority;
    private Boolean all;
    private Boolean status;

    private List<Integer> permission;
}
