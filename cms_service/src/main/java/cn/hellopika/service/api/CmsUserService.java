package cn.hellopika.service.api;

import cn.hellopika.service.dto.CmsUserDto;

public interface CmsUserService {

    CmsUserDto findByUsername(String username);
}
