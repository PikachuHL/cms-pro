package cn.hellopika.service.converter;

import cn.hellopika.core.foundation.BaseConverter;
import cn.hellopika.dao.entity.CmsSiteEntity;
import cn.hellopika.service.dto.CmsSiteDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsSiteConverter extends BaseConverter<CmsSiteEntity, CmsSiteDto> {
    CmsSiteConverter CONVERTER = Mappers.getMapper(CmsSiteConverter.class);
}
