package cn.hellopika.service.converter;

import cn.hellopika.dao.entity.CmsSiteEntity;
import cn.hellopika.service.dto.CmsSiteDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsSiteConverter {
    CmsSiteConverter CONVERTER = Mappers.getMapper(CmsSiteConverter.class);

    /**
     * entity 转换为 dto
     *
     * @param entity
     * @return
     */
    CmsSiteDto entityToDto(CmsSiteEntity entity);

    /**
     * dto 转换为 entity
     *
     * @param dto
     * @return
     */
    CmsSiteEntity dtoToEntity(CmsSiteDto dto);
}
