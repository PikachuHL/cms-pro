package cn.hellopika.dao.mapper;

import cn.hellopika.core.foundation.BaseMapper;
import cn.hellopika.dao.entity.CmsPermissionEntity;

import java.util.List;

public interface CmsPermissionMapper extends BaseMapper<CmsPermissionEntity, Integer> {

    // 在删除一个权限的时候, 该权限不能有子权限
    List<CmsPermissionEntity> selectByParentId(Integer id);

}
