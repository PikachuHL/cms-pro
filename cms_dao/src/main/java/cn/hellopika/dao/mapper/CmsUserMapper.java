package cn.hellopika.dao.mapper;


import cn.hellopika.dao.entity.CmsUserEntity;

public interface CmsUserMapper {

    CmsUserEntity findByUsername(String username);

}
