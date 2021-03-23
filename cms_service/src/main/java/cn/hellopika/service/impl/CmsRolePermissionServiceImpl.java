package cn.hellopika.service.impl;

import cn.hellopika.dao.mapper.CmsRolePermissionMapper;
import cn.hellopika.service.api.CmsRolePermissionService;
import cn.hellopika.service.dto.CmsRolePermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsRolePermissionServiceImpl implements CmsRolePermissionService {

    @Autowired
    private CmsRolePermissionMapper cmsRolePermissionMapper;

    @Override
    public void save(CmsRolePermissionDto dto) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsRolePermissionDto dto) {

    }

    @Override
    public CmsRolePermissionDto selectById(Integer id) {
        return null;
    }

    @Override
    public List<CmsRolePermissionDto> selectAll() {
        return null;
    }

    @Override
    public List<Integer> selectPermissionIdsByRoleId(Integer roleId) {
        return cmsRolePermissionMapper.selectPermissionIdsByRoleId(roleId);
    }
}
