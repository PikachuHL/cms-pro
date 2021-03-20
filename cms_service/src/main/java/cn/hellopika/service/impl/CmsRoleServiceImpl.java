package cn.hellopika.service.impl;

import cn.hellopika.dao.entity.CmsRoleEntity;
import cn.hellopika.dao.mapper.CmsRoleMapper;
import cn.hellopika.dao.mapper.CmsRolePermissionMapper;
import cn.hellopika.service.api.CmsRoleService;
import cn.hellopika.service.converter.CmsRoleConverter;
import cn.hellopika.service.dto.CmsRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsRoleServiceImpl implements CmsRoleService {

    @Autowired
    private CmsRoleMapper cmsRoleMapper;

    @Autowired
    private CmsRolePermissionMapper cmsRolePermissionMapper;

    @Override
    public void save(CmsRoleDto dto) {
        CmsRoleEntity cmsRoleEntity = CmsRoleConverter.CONVERTER.dtoToEntity(dto);
        cmsRoleMapper.save(cmsRoleEntity);
        System.out.println(dto.getPermission());
        cmsRolePermissionMapper.batchInsert(dto.getPermission(), cmsRoleEntity.getId());
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsRoleDto dto) {

    }

    @Override
    public CmsRoleDto selectById(Integer id) {
        return null;
    }

    @Override
    public List<CmsRoleDto> selectAll() {
        return null;
    }
}
