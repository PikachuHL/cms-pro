package cn.hellopika.dao.mapper;

import cn.hellopika.core.foundation.BaseMapper;
import cn.hellopika.dao.entity.CmsRolePermissionEntity;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface CmsRolePermissionMapper extends BaseMapper<CmsRolePermissionEntity, Integer> {

    void batchInsert(@Param("permissionList")List<Integer> permissionList, @Param("roleId")Integer roleId);

}
