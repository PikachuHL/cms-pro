package cn.hellopika.service.converter;


import cn.hellopika.core.foundation.BaseConverter;
import cn.hellopika.dao.entity.CmsUserPrimaryEntity;
import cn.hellopika.service.dto.CmsUserPrimaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 使用 MapStruct 进行 entity 和 dto 的相互转化
 */
@Mapper
public interface CmsUserPrimaryConverter extends BaseConverter<CmsUserPrimaryEntity, CmsUserPrimaryDto> {
    CmsUserPrimaryConverter CONVERTER = Mappers.getMapper(CmsUserPrimaryConverter.class);
}
