package cn.hellopika.service.api;

import cn.hellopika.service.dto.CmsUserDto;

public interface CmsUserService {

    /**
     * 根据名称查找用户
     * @param username
     * @return
     */
    CmsUserDto findByUsername(String username);

    /**
     * 更新用户
     * @param cmsUserDto
     */
    void updateUser(CmsUserDto cmsUserDto);
}
