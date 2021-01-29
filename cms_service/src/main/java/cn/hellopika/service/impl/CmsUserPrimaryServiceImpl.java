package cn.hellopika.service.impl;

import cn.hellopika.dao.entity.CmsUserPrimaryEntity;
import cn.hellopika.dao.mapper.CmsUserPrimaryMapper;
import cn.hellopika.service.api.CmsUserPrimaryService;
import cn.hellopika.service.converter.CmsUserPrimaryConverter;
import cn.hellopika.service.dto.CmsUserPrimaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsUserPrimaryServiceImpl implements CmsUserPrimaryService {

    @Autowired
    private CmsUserPrimaryMapper cmsUserPrimaryMapper;

    @Override
    public CmsUserPrimaryDto findByUsername(String username) {

        CmsUserPrimaryEntity cmsUserPrimaryEntity = cmsUserPrimaryMapper.findByUsername(username);
        // 把 entity 转换成 dto
        CmsUserPrimaryDto cmsUserPrimaryDto = CmsUserPrimaryConverter.CONVERTER.entityToDto(cmsUserPrimaryEntity);

        return cmsUserPrimaryDto;
    }
}
