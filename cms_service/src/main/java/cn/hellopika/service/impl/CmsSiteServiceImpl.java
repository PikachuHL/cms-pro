package cn.hellopika.service.impl;

import cn.hellopika.dao.mapper.CmsSiteMapper;
import cn.hellopika.service.api.CmsSiteService;
import cn.hellopika.service.converter.CmsSiteConverter;
import cn.hellopika.service.dto.CmsSiteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsSiteServiceImpl implements CmsSiteService {

    @Autowired
    private CmsSiteMapper cmsSiteMapper;

    @Override
    public CmsSiteDto getById(int id) {
        return CmsSiteConverter.CONVERTER.entityToDto(cmsSiteMapper.getById(id));
    }

    @Override
    public void update(CmsSiteDto cmsSiteDto) {
        cmsSiteMapper.update(CmsSiteConverter.CONVERTER.dtoToEntity(cmsSiteDto));
    }
}
