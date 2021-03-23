package cn.hellopika.dao.mapper;

import cn.hellopika.core.foundation.BaseMapper;
import cn.hellopika.dao.entity.CmsRoleEntity;

import java.util.List;

public interface CmsRoleMapper extends BaseMapper<CmsRoleEntity, Integer> {
    List<CmsRoleEntity> getPage(CmsRoleEntity entity);
}
