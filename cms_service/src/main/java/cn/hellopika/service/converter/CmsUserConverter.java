package cn.hellopika.service.converter;


import cn.hellopika.dao.entity.CmsUserEntity;
import cn.hellopika.dao.enums.converter.UserStatusConverter;
import cn.hellopika.service.dto.CmsUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 使用 MapStruct 进行 entity 和 dto 的相互转化
 */
@Mapper(uses = UserStatusConverter.class)
public interface CmsUserConverter {
    CmsUserConverter CONVERTER = Mappers.getMapper(CmsUserConverter.class);

    /**
     * entity 转换为 dto
     *
     * @param entity
     * @return
     */
    CmsUserDto entityToDto(CmsUserEntity entity);


    /**
     * dto 转换为 entity
     *
     * @param dto
     * @return
     */
    CmsUserEntity dtoToEntity(CmsUserDto dto);


}
