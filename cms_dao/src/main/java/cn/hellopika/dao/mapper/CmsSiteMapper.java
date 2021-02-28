package cn.hellopika.dao.mapper;

import cn.hellopika.dao.entity.CmsSiteEntity;

public interface CmsSiteMapper {

    CmsSiteEntity getById(int id);

    void update(CmsSiteEntity cmsSiteEntity);
}
