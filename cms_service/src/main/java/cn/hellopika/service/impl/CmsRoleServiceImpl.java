package cn.hellopika.service.impl;

import cn.hellopika.context.utils.UtilsHttp;
import cn.hellopika.core.foundation.BasePage;
import cn.hellopika.dao.entity.CmsRoleEntity;
import cn.hellopika.dao.mapper.CmsRoleMapper;
import cn.hellopika.dao.mapper.CmsRolePermissionMapper;
import cn.hellopika.service.api.CmsRoleService;
import cn.hellopika.service.converter.CmsRoleConverter;
import cn.hellopika.service.dto.CmsRoleDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CmsRoleServiceImpl implements CmsRoleService {

    @Autowired
    private CmsRoleMapper cmsRoleMapper;

    @Autowired
    private CmsRolePermissionMapper cmsRolePermissionMapper;

    /**
     * 添加角色
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)  // 添加事务，有任何异常都回滚
    public void save(CmsRoleDto dto) {
        CmsRoleEntity cmsRoleEntity = CmsRoleConverter.CONVERTER.dtoToEntity(dto);
        cmsRoleMapper.save(cmsRoleEntity);

        // 当角色没有全部权限的时候，把角色和权限的关系插入 角色-权限 中间表
        if (!dto.getAll()) {
            if (Objects.nonNull(dto.getPermission())) {
                cmsRolePermissionMapper.batchInsert(dto.getPermission(), cmsRoleEntity.getId());
            }
        }

    }

    /**
     * 删除角色
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        // 先删除中间表的记录
        cmsRolePermissionMapper.deleteByRoleId(id);

        cmsRoleMapper.deleteById(id);
    }

    /**
     * 更新角色
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CmsRoleDto dto) {
        CmsRoleEntity cmsRoleEntity = CmsRoleConverter.CONVERTER.dtoToEntity(dto);
        cmsRoleMapper.update(cmsRoleEntity);

        // 往 角色-权限 中间表添加信息
        // 添加信息之前，先把中间表中该角色的相关记录删除
        cmsRolePermissionMapper.deleteByRoleId(dto.getId());
        if (!dto.getAll()) {
            if (Objects.nonNull(dto.getPermission())) {
                cmsRolePermissionMapper.batchInsert(dto.getPermission(), cmsRoleEntity.getId());
            }
        }

    }

    @Override
    public CmsRoleDto selectById(Integer id) {
        return CmsRoleConverter.CONVERTER.entityToDto(cmsRoleMapper.selectById(id));
    }

    @Override
    public List<CmsRoleDto> selectAll() {
        return CmsRoleConverter.CONVERTER.entityToDto(cmsRoleMapper.selectAll());
    }

    @Override
    public BasePage<CmsRoleDto> getPage(CmsRoleDto dto) {
        // 获取前端传来的 分页相关信息
        UtilsHttp.MyPageInfo pageInfo = UtilsHttp.getPageInfo();

        // 使用 PageHelper 执行分页操作
        Page<CmsRoleDto> page = PageHelper.startPage(pageInfo.getPageCurrent(), pageInfo.getPageSize()).doSelectPage(() -> CmsRoleConverter.CONVERTER.entityToDto(cmsRoleMapper.getPage(CmsRoleConverter.CONVERTER.dtoToEntity(dto))));

        return new BasePage<>(page.getTotal(), page.getResult());
    }
}
