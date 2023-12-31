package top.extrame.common.tool.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import top.extrame.common.tool.model.Result;

/**
 * 响应统一回复类
 *
 * @param <T> 携带数据类型
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Result {

    private final Integer status;
    private final String message;
    private T data;

    public ResponseResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseResult(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @Override
    public Integer getStatus() {
        return this.status;
    }

    public T getData() {
        return this.data;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
