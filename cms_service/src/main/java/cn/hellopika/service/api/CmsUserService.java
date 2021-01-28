package cn.hellopika.service.api;

import cn.hellopika.service.dto.CmsUserPrimaryDto;

public interface CmsUserService {

    CmsUserPrimaryDto findByUsername(String username);
}
