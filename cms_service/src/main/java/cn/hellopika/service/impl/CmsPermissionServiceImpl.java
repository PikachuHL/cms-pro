package cn.hellopika.service.impl;

import cn.hellopika.core.exception.BusinessException;
import cn.hellopika.dao.entity.CmsPermissionEntity;
import cn.hellopika.dao.mapper.CmsPermissionMapper;
import cn.hellopika.dao.mapper.CmsRolePermissionMapper;
import cn.hellopika.service.api.CmsPermissionService;
import cn.hellopika.service.converter.CmsPermissionConverter;
import cn.hellopika.service.dto.CmsPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class CmsPermissionServiceImpl implements CmsPermissionService {

    @Autowired
    private CmsPermissionMapper cmsPermissionMapper;

    @Autowired
    private CmsRolePermissionMapper cmsRolePermissionMapper;

    @Override
    public void save(CmsPermissionDto dto) {
        cmsPermissionMapper.save(CmsPermissionConverter.CONVERTER.dtoToEntity(dto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        // 删除权限之前先查询该权限是否有子权限
        List<CmsPermissionEntity> cmsPermissionEntities = cmsPermissionMapper.selectByParentId(id);
        if (!CollectionUtils.isEmpty(cmsPermissionEntities)){
            throw new BusinessException("只能删除底层权限");
        }

        // 删除权限的同时删除 角色-权限 表中的记录
        cmsRolePermissionMapper.deleteByPermissionId(id);
        // 删除权限
        cmsPermissionMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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


    /**
     * 构建树形结构
     * @param excludeId
     * @return
     */
    @Override
    public List<CmsPermissionDto> treeBuilder(Integer excludeId){
        List<CmsPermissionDto> permissionList = selectAll();

        // 新建map用于存放 id 和 id所对应的dto
        Map<Integer, CmsPermissionDto> permissionMap = new HashMap<>();

        // 仅用于存放 顶层菜单
        List<CmsPermissionDto> top = new ArrayList<>();

        permissionList.forEach(x -> {
            Integer id = x.getId();
            // 如果在修改页面, 需要把 小于等于自己级别的元素 不在前端显示, 就要从当前元素把链断开
            if (Objects.nonNull(excludeId) && id.compareTo(excludeId) == 0) {
                return; // 这里的return会跳出当前循环
            }
            permissionMap.put(id, x);

            Integer parentId = x.getParentId();

            if (parentId == 0) {
                top.add(x);
            } else {
                CmsPermissionDto parentDto = permissionMap.get(parentId);
                // 如果排除了相关元素, parentDto可能为空, 需要判断一下
                if (Objects.isNull(parentDto) && Objects.nonNull(excludeId) && parentId.compareTo(excludeId) == 0) {
                    return;
                }
                List<CmsPermissionDto> children = parentDto.getChildren();

                if (CollectionUtils.isEmpty(children)) {
                    children = new ArrayList<>();
                }
                children.add(x);

                parentDto.setChildren(children);
            }
        });

        return top;
    }
}
