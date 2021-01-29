package cn.hellopika.service.api;

import cn.hellopika.service.dto.CmsUserPrimaryDto;

public interface CmsUserPrimaryService {

    CmsUserPrimaryDto findByUsername(String username);
}
