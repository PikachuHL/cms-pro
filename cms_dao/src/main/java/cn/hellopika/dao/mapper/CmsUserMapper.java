package cn.hellopika.dao.mapper;


import cn.hellopika.core.foundation.BaseMapper;
import cn.hellopika.dao.entity.CmsUserEntity;

import java.util.List;

public interface CmsUserMapper extends BaseMapper<CmsUserEntity, Integer> {

    /**
     * 根据名称查询
     *
     * @param username
     * @return
     */
    CmsUserEntity findByUsername(String username);

    List<CmsUserEntity> getPage(CmsUserEntity entity);
}
