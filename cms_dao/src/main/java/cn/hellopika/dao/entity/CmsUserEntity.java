package cn.hellopika.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 用户附表的 entity
 */
@Getter
@Setter
public class CmsUserEntity {
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer id;
    private String username;
    private Integer status;
}
