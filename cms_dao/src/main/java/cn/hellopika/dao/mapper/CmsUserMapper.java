package cn.hellopika.dao.mapper;


import cn.hellopika.dao.entity.CmsUserEntity;

public interface CmsUserMapper {

    /**
     * 根据名称查询
     * @param username
     * @return
     */
    CmsUserEntity findByUsername(String username);


    /**
     * 更新用户
     * @param cmsUserEntity
     */
    void updateUser(CmsUserEntity cmsUserEntity);
}
