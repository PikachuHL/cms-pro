package cn.hellopika.service.dto;

import cn.hellopika.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmsLogDto extends BaseDto<Integer> {
    private Integer userId;
    private String username;
    private String loginIp;
    private String url;
    private String content;
}
