package cn.hellopika.service.impl;

import cn.hellopika.core.exception.BusinessException;
import cn.hellopika.dao.entity.CmsPermissionEntity;
import cn.hellopika.dao.mapper.CmsPermissionMapper;
import cn.hellopika.service.api.CmsPermissionService;
import cn.hellopika.service.converter.CmsPermissionConverter;
import cn.hellopika.service.dto.CmsPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CmsPermissionServiceImpl implements CmsPermissionService {

    @Autowired
    private CmsPermissionMapper cmsPermissionMapper;

    @Override
    public void save(CmsPermissionDto dto) {
        cmsPermissionMapper.save(CmsPermissionConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public void deleteById(Integer id) {
        // 删除权限之前先查询该权限是否有子权限
        List<CmsPermissionEntity> cmsPermissionEntities = cmsPermissionMapper.selectByParentId(id);
        if (!CollectionUtils.isEmpty(cmsPermissionEntities)){
            throw new BusinessException("只能删除底层权限");
        }

        // 删除权限
        cmsPermissionMapper.deleteById(id);
    }

    @Override
    public void update(CmsPermissionDto dto) {
        cmsPermissionMapper.update(CmsPermissionConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    public CmsPermissionDto selectById(Integer id) {
        return CmsPermissionConverter.CONVERTER.entityToDto(cmsPermissionMapper.selectById(id));
    }

    @Override
    public List<CmsPermissionDto> selectAll() {
        return CmsPermissionConverter.CONVERTER.entityToDto(cmsPermissionMapper.selectAll());
    }
}
