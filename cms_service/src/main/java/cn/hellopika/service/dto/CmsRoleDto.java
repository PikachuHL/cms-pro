package cn.hellopika.service.dto;

import cn.hellopika.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Getter
@Setter
public class CmsRoleDto extends BaseDto<Integer> {
    @NotBlank(message = "请输入角色名称")
    private String name;
    private Integer priority;
    private Boolean all;
    private Boolean status;

    private List<Integer> permission;
}
