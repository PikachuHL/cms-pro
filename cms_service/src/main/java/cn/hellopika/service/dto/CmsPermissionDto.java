package cn.hellopika.service.dto;

import cn.hellopika.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CmsPermissionDto extends BaseDto<Integer> {
    private Integer parentId;
    private Boolean menu;
    private String icon;

    @NotBlank(message = "请输入权限名称")
    private String name;

    private String action;
    private String url;

    @NotNull(message = "排序不能为空")
    @Min(value = 0, message = "序号不能小于0")
    @Max(value = 9999, message = "序号不能大于9999")
    private Integer priority;

    private List<CmsPermissionDto> children;
}
