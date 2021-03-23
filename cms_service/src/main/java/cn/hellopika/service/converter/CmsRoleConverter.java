package cn.hellopika.service.converter;

import cn.hellopika.core.foundation.BaseConverter;
import cn.hellopika.dao.entity.CmsRoleEntity;
import cn.hellopika.service.dto.CmsRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CmsRoleConverter extends BaseConverter<CmsRoleEntity, CmsRoleDto> {
    CmsRoleConverter CONVERTER = Mappers.getMapper(CmsRoleConverter.class);

    List<CmsRoleDto> entityToDto(List<CmsRoleEntity> entity);
}
