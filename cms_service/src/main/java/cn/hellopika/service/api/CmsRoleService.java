package cn.hellopika.service.api;

import cn.hellopika.core.foundation.BasePage;
import cn.hellopika.core.foundation.BaseService;
import cn.hellopika.service.dto.CmsRoleDto;

import java.util.List;

public interface CmsRoleService extends BaseService<CmsRoleDto, Integer> {
    BasePage<CmsRoleDto> getPage(CmsRoleDto dto);
}
