package cn.hellopika.service.dto;

import cn.hellopika.core.foundation.BaseDto;
import cn.hellopika.dao.enums.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsUserDto extends BaseDto<Integer> {
    private String username;
    private UserStatusEnum status;
    private Boolean admin;
    private String lastLoginIp;
    private String sessionId;
    /**
     * 超级管理员
     */
    private Boolean administrator;
}
