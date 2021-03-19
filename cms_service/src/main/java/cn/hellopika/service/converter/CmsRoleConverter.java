package cn.hellopika.service.converter;

import cn.hellopika.core.foundation.BaseConverter;
import cn.hellopika.dao.entity.CmsRoleEntity;
import cn.hellopika.service.dto.CmsRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsRoleConverter extends BaseConverter<CmsRoleEntity, CmsRoleDto> {
    CmsRoleConverter CONVERTER = Mappers.getMapper(CmsRoleConverter.class);
}
