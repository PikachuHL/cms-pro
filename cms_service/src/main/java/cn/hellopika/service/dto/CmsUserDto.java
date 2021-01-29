package cn.hellopika.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CmsUserDto {
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer id;
    private String username;
    private Boolean status;
}
