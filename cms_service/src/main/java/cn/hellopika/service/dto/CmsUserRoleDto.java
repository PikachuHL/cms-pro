package cn.hellopika.service.dto;

import cn.hellopika.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsUserRoleDto extends BaseDto<Integer> {
    private Integer UserId;
    private Integer RoleId;
}
