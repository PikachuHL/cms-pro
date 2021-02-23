package cn.hellopika.service.api;

import cn.hellopika.service.dto.CmsLogDto;

public interface CmsLogService {
    /**
     * 保存日志
     */
    void save(CmsLogDto logDto);
}
