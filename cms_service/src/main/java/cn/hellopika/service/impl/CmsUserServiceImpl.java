package cn.hellopika.service.impl;

import cn.hellopika.dao.entity.CmsUserEntity;
import cn.hellopika.dao.mapper.CmsUserMapper;
import cn.hellopika.service.api.CmsUserService;
import cn.hellopika.service.converter.CmsUserConverter;
import cn.hellopika.service.dto.CmsUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsUserServiceImpl implements CmsUserService {

    @Autowired
    private CmsUserMapper cmsUserMapper;

    @Override
    public CmsUserDto findByUsername(String username) {

        CmsUserEntity cmsUserEntity = cmsUserMapper.findByUsername(username);
        // 把 entity 转换成 dto
        CmsUserDto cmsUserDto = CmsUserConverter.CONVERTER.entityToDto(cmsUserEntity);

        return cmsUserDto;
    }
}
