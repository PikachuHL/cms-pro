package cn.hellopika.service.api;

import cn.hellopika.core.foundation.BasePage;
import cn.hellopika.core.foundation.BaseService;
import cn.hellopika.service.dto.CmsUserDto;

import java.util.List;

public interface CmsUserService extends BaseService<CmsUserDto, Integer> {

    /**
     * 根据 用户名 查找用户
     * @param username  用户名
     * @return          userDto
     */
    CmsUserDto findByUsername(String username);

    /**
     * 根据 邮箱 查找用户
     * @param email 用户邮箱
     * @return      userDto
     */
    CmsUserDto findByEmail(String email);

    /**
     * 获取用户首页的分页数据
     * @param dto
     * @return
     */
    BasePage<CmsUserDto> getPage(CmsUserDto dto);
}
