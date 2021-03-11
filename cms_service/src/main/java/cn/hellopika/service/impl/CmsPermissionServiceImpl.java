package cn.hellopika.service.impl;

import cn.hellopika.dao.mapper.CmsPermissionMapper;
import cn.hellopika.service.api.CmsPermissionService;
import cn.hellopika.service.converter.CmsPermissionConverter;
import cn.hellopika.service.dto.CmsPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsPermissionServiceImpl implements CmsPermissionService {

    @Autowired
    private CmsPermissionMapper cmsPermissionMapper;

    @Override
    public void save(CmsPermissionDto dto) {
        cmsPermissionMapper.save(CmsPermissionConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void update(CmsPermissionDto dto) {

    }

    @Override
    public CmsPermissionDto selectById(int id) {
        return null;
    }
}
