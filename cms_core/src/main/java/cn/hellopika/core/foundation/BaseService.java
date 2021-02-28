package cn.hellopika.core.foundation;

import java.io.Serializable;

public interface BaseService<D extends BaseDto<PK>, PK extends Serializable> {
    /**
     * 新增
     * @param dto
     */
    void save(D dto);

    /**
     * 修改
     * @param dto
     */
    void update(D dto);

    /**
     * 根据 id 查询
     * @param id
     * @return
     */
    D selectById(int id);
}
