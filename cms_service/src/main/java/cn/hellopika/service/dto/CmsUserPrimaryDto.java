package cn.hellopika.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CmsUserPrimaryDto {
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer loginCount;
}
