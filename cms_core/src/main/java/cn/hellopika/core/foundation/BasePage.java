package cn.hellopika.core.foundation;

import java.io.Serializable;
import java.util.List;

/**
 * 给前端返回分页信息的类。
 * @param <T>
 */

public class BasePage<T> implements Serializable {

    private Long pageCount;
    private List<T> content;

    public BasePage(Long pageCount, List<T> content) {
        this.pageCount = pageCount;
        this.content = content;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
