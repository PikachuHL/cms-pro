package cn.hellopika.core.foundation;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<E extends BaseEntity<PK>, PK extends Serializable> {

    /**
     * 新增
     * @param entity
     */
    void save(E entity);

    /**
     * 根据 id 删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 修改
     * @param entity
     */
    void update(E entity);

    /**
     * 根据 id 查询
     * @param id
     * @return
     */
    E selectById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<E> selectAll();


}
