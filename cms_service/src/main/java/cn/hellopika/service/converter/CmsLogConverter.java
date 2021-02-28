package cn.hellopika.service.converter;


import cn.hellopika.core.foundation.BaseConverter;
import cn.hellopika.dao.entity.CmsLogEntity;
import cn.hellopika.service.dto.CmsLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 使用 MapStruct 进行 entity 和 dto 的相互转化
 */
@Mapper
public interface CmsLogConverter extends BaseConverter<CmsLogEntity, CmsLogDto> {
    CmsLogConverter CONVERTER = Mappers.getMapper(CmsLogConverter.class);
}
