package cn.hellopika.core.foundation;

import java.io.Serializable;
import java.util.List;

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

    /**
     * 查询所有
     * @return
     */
    List<D> selectAll();
}
