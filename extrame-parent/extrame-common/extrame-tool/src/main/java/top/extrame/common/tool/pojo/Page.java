package top.extrame.common.tool.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Page<T> {
    /**
     * 当前页码
     */
    private Integer offset;
    /**
     * 每页大小
     */
    private Integer size;
    /**
     * 总页数
     */
    private Long total;
    /**
     * 携带数据
     */
    private T data;

    public Page() {}

    public Page(Integer offset, Integer size, Long total, T data) {
        this.offset = offset;
        this.size = size;
        this.total = total;
        this.data = data;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
