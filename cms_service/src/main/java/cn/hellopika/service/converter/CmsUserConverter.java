package cn.hellopika.service.converter;


import cn.hellopika.core.foundation.BaseConverter;
import cn.hellopika.dao.entity.CmsUserEntity;
import cn.hellopika.dao.enums.converter.UserStatusConverter;
import cn.hellopika.service.dto.CmsUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 使用 MapStruct 进行 entity 和 dto 的相互转化
 */
@Mapper(uses = UserStatusConverter.class)
public interface CmsUserConverter extends BaseConverter<CmsUserEntity, CmsUserDto> {
    CmsUserConverter CONVERTER = Mappers.getMapper(CmsUserConverter.class);
}
