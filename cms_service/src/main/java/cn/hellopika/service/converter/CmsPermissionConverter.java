package cn.hellopika.service.converter;

import cn.hellopika.core.foundation.BaseConverter;
import cn.hellopika.dao.entity.CmsPermissionEntity;
import cn.hellopika.service.dto.CmsPermissionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsPermissionConverter extends BaseConverter<CmsPermissionEntity, CmsPermissionDto> {
    CmsPermissionConverter CONVERTER = Mappers.getMapper(CmsPermissionConverter.class);
}
