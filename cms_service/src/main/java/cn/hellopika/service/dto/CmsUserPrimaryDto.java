package cn.hellopika.service.dto;

import cn.hellopika.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CmsUserPrimaryDto extends BaseDto<Integer> {
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer loginCount;
}
