package cn.hellopika.core.foundation;


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
}
