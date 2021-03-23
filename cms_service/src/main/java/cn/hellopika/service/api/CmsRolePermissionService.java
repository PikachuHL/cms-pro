package cn.hellopika.service.api;

import cn.hellopika.core.foundation.BaseService;
import cn.hellopika.service.dto.CmsRolePermissionDto;

import java.util.List;

public interface CmsRolePermissionService extends BaseService<CmsRolePermissionDto, Integer> {
    List<Integer> selectPermissionIdsByRoleId(Integer roleId);
}
