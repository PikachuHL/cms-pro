package cn.hellopika.dao.mapper;

import cn.hellopika.core.foundation.BaseMapper;
import cn.hellopika.dao.entity.CmsRolePermissionEntity;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface CmsRolePermissionMapper extends BaseMapper<CmsRolePermissionEntity, Integer> {
    /**
     *
     * 新增角色时 批量添加数据
     * @param permissionList
     * @param roleId
     */
    void batchInsert(@Param("permissionList")List<Integer> permissionList, @Param("roleId")Integer roleId);

    /**
     * 删除权限时，根据 PermissionId 删除对应的记录
     * @param permissionId
     */
    void deleteByPermissionId(Integer permissionId);

    /**
     * 删除角色时，根据 roleId 删除对应的记录
     * @param roleId
     */
    void deleteByRoleId(Integer roleId);


    /**
     * 根据 roleId 查找 PermissionId
     * @param roleId
     * @return
     */
    List<Integer> selectPermissionIdsByRoleId(Integer roleId);

}
