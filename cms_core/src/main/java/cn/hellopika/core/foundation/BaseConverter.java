package cn.hellopika.core.foundation;


import java.util.List;

public interface BaseConverter<E extends BaseEntity, D extends BaseDto> {
    /**
     * entity 转换为 dto
     *
     * @param entity
     * @return
     */
    D entityToDto(E entity);

    /**
     * dto 转换为 entity
     *
     * @param dto
     * @return
     */
    E dtoToEntity(D dto);

    /**
     * entity的list 转换为 dto的list
     * @param entityList
     * @return
     */
    List<D> entityToDto(List<E> entityList);
}
