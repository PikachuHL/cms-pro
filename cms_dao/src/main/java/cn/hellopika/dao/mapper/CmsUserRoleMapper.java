package cn.hellopika.dao.mapper;

import cn.hellopika.core.foundation.BaseMapper;
import cn.hellopika.dao.entity.CmsUserRoleEntity;

public interface CmsUserRoleMapper extends BaseMapper<CmsUserRoleEntity, Integer> {
    /**
     * 根据用户 id 删除中间表数据
     * @param userId
     */
    void deleteByUserId(Integer userId);

    /**
     * 根据角色 id 删除中间表数据
     * @param roleId
     */
    void deleteByRoleId(Integer roleId);
}
