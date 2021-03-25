package cn.hellopika.service.impl;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import cn.hellopika.context.utils.UtilsHttp;
import cn.hellopika.context.utils.UtilsShiro;
import cn.hellopika.core.foundation.BasePage;
import cn.hellopika.dao.entity.CmsUserEntity;
import cn.hellopika.dao.entity.CmsUserRoleEntity;
import cn.hellopika.dao.mapper.CmsUserMapper;
import cn.hellopika.dao.mapper.CmsUserRoleMapper;
import cn.hellopika.service.api.CmsUserService;
import cn.hellopika.service.converter.CmsUserConverter;
import cn.hellopika.service.dto.CmsUserDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CmsUserServiceImpl implements CmsUserService {

    @Autowired
    private CmsUserMapper cmsUserMapper;

    @Autowired
    private CmsUserRoleMapper cmsUserRoleMapper;

    @Override
    public CmsUserDto findByUsername(String username) {
        return  CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.findByUsername(username));
    }

    @Override
    public CmsUserDto findByEmail(String email) {
        return CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.findByEmail(email));
    }


    @Override
    public BasePage<CmsUserDto> getPage(CmsUserDto dto) {
        // 获取前端传来的 分页相关信息
        UtilsHttp.MyPageInfo pageInfo = UtilsHttp.getPageInfo();

        // 使用 PageHelper 执行分页操作
        Page<CmsUserDto> page = PageHelper.startPage(pageInfo.getPageCurrent(), pageInfo.getPageSize()).doSelectPage(() -> CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.getPage(CmsUserConverter.CONVERTER.dtoToEntity(dto))));

        return new BasePage<>(page.getTotal(), page.getResult());
    }

    @Override
    public void save(CmsUserDto dto) {
        
        // 设置密码盐
        String salt = UtilsShiro.generateSalt();
        dto.setSalt(salt);

        // 加密密码
        String encriptedPassword = UtilsShiro.encryptPassword(dto.getPassword(), salt);
        dto.setPassword(encriptedPassword);

        CmsUserEntity cmsUserEntity = CmsUserConverter.CONVERTER.dtoToEntity(dto);
        cmsUserMapper.save(cmsUserEntity);

        // 把用户的角色数据保存到中间表中
        if(Objects.nonNull(dto.getRoleId())){
            CmsUserRoleEntity cmsUserRoleEntity = new CmsUserRoleEntity();
            cmsUserRoleEntity.setUserId(cmsUserEntity.getId());
            cmsUserRoleEntity.setRoleId(dto.getRoleId());
            cmsUserRoleMapper.save(cmsUserRoleEntity);
        }

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsUserDto cmsUserDto) {
        cmsUserMapper.update(CmsUserConverter.CONVERTER.dtoToEntity(cmsUserDto));
    }

    @Override
    public CmsUserDto selectById(Integer id) {
        return null;
    }

    @Override
    public List<CmsUserDto> selectAll() {
        return CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.selectAll());
    }
}
