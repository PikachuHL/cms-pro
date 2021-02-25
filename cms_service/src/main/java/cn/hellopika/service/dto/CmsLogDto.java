package cn.hellopika.service.dto;

import cn.hellopika.core.foundation.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CmsLogDto extends BaseDto<Integer> {
    private Integer userId;
    private String username;
    private String loginIp;
    private String url;
    private String content;

    public static CmsLogDto setCmsLogDto(Integer userId, String username, String loginIp, String url, String content){
        CmsLogDto cmsLogDto = new CmsLogDto();
        cmsLogDto.setUserId(userId);
        cmsLogDto.setUsername(username);
        cmsLogDto.setLoginIp(loginIp);
        cmsLogDto.setUrl(url);
        cmsLogDto.setContent(content);
        return cmsLogDto;
    }
}
