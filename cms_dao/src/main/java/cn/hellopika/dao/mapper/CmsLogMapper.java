package cn.hellopika.dao.mapper;

import cn.hellopika.dao.entity.CmsLogEntity;

public interface CmsLogMapper {
    /**
     * 保存日志
     */
    void save(CmsLogEntity logEntity);
}
