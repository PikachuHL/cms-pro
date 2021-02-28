package cn.hellopika.service.impl;

import cn.hellopika.dao.entity.CmsLogEntity;
import cn.hellopika.dao.mapper.CmsLogMapper;
import cn.hellopika.service.api.CmsLogService;
import cn.hellopika.service.converter.CmsLogConverter;
import cn.hellopika.service.dto.CmsLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsLogServiceImpl implements CmsLogService {

    @Autowired
    private CmsLogMapper cmsLogMapper;

    @Override
    public void save(CmsLogDto logDto) {
        CmsLogEntity cmsLogEntity = CmsLogConverter.CONVERTER.dtoToEntity(logDto);

        cmsLogMapper.save(cmsLogEntity);
    }

    @Override
    public void update(CmsLogDto dto) {

    }

    @Override
    public CmsLogDto selectById(int id) {
        return null;
    }
}
