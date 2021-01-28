package cn.hellopika.dao.mapper;


import cn.hellopika.dao.entity.CmsUserPrimaryEntity;

public interface CmsUserPrimaryMapper {

    CmsUserPrimaryEntity findByUsername(String username);

}
