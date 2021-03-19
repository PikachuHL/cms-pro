package cn.hellopika.service.converter;

import cn.hellopika.core.foundation.BaseConverter;
import cn.hellopika.dao.entity.CmsRolePermissionEntity;
import cn.hellopika.service.dto.CmsRolePermissionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsRolePermissionConverter extends BaseConverter<CmsRolePermissionEntity, CmsRolePermissionDto> {
    CmsRolePermissionConverter CONVERTER = Mappers.getMapper(CmsRolePermissionConverter.class);
}
