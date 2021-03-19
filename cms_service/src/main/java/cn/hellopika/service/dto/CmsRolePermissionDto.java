package cn.hellopika.service.dto;

import cn.hellopika.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsRolePermissionDto extends BaseDto<Integer> {
    private Integer roleId;
    private Integer permissionId;
}
