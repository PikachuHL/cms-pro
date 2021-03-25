package cn.hellopika.dao.mapper;


import cn.hellopika.core.foundation.BaseMapper;
import cn.hellopika.dao.entity.CmsUserEntity;

import java.util.List;

public interface CmsUserMapper extends BaseMapper<CmsUserEntity, Integer> {

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    CmsUserEntity findByUsername(String username);

    /**
     * 根据邮箱查询
     * @param email
     * @return
     */
    CmsUserEntity findByEmail(String email);

    List<CmsUserEntity> getPage(CmsUserEntity entity);
}
