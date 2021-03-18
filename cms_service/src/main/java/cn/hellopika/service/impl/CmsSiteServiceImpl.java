package cn.hellopika.service.impl;

import cn.hellopika.dao.mapper.CmsSiteMapper;
import cn.hellopika.service.api.CmsSiteService;
import cn.hellopika.service.converter.CmsSiteConverter;
import cn.hellopika.service.dto.CmsSiteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsSiteServiceImpl implements CmsSiteService {

    @Autowired
    private CmsSiteMapper cmsSiteMapper;

    @Override
    public void save(CmsSiteDto dto) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsSiteDto cmsSiteDto) {
        cmsSiteMapper.update(CmsSiteConverter.CONVERTER.dtoToEntity(cmsSiteDto));
    }

    @Override
    public CmsSiteDto selectById(Integer id) {
        return CmsSiteConverter.CONVERTER.entityToDto(cmsSiteMapper.selectById(id));
    }

    @Override
    public List<CmsSiteDto> selectAll() {
        return null;
    }
}
