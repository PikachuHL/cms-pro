package cn.hellopika.service.converter;

import cn.hellopika.core.foundation.BaseConverter;
import cn.hellopika.dao.entity.CmsUserRoleEntity;
import cn.hellopika.service.dto.CmsUserRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsUserRoleConverter extends BaseConverter<CmsUserRoleEntity, CmsUserRoleDto> {
    CmsUserRoleConverter CONVERTER = Mappers.getMapper(CmsUserRoleConverter.class);
}
