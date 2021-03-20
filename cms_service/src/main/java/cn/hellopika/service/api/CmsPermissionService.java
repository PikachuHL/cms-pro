package cn.hellopika.service.api;

import cn.hellopika.core.foundation.BaseService;
import cn.hellopika.service.dto.CmsPermissionDto;

import java.util.List;

public interface CmsPermissionService extends BaseService<CmsPermissionDto, Integer> {
    List<CmsPermissionDto> treeBuilder(Integer excludeId);
}
