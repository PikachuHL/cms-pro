package cn.hellopika.service.api;

import cn.hellopika.core.foundation.BasePage;
import cn.hellopika.core.foundation.BaseService;
import cn.hellopika.service.dto.CmsUserDto;

import java.util.List;

public interface CmsUserService extends BaseService<CmsUserDto, Integer> {

    /**
     * 根据名称查找用户
     * @param username
     * @return
     */
    CmsUserDto findByUsername(String username);

    BasePage<CmsUserDto> getPage(CmsUserDto dto);
}
