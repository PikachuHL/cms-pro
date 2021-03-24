package cn.hellopika.service.dto;

import cn.hellopika.core.foundation.BaseDto;
import cn.hellopika.dao.enums.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class CmsUserDto extends BaseDto<Integer> {
    private String username;
    private Boolean status;
    private String email;
    private String password;
    private String salt;
    /**
     * 超级管理员
     */
    private Boolean administrator;

    private LocalDateTime registerTime;
}
