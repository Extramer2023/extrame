package top.extrame.common.exception.domain.module.enums;

import top.extrame.common.tool.model.Result;
import top.extrame.common.tool.pojo.ResponseResult;

/**
 * The interface Response enum.
 *
 * @author Jx -zou
 */
public interface ResponseEnum extends Result {

    default ResponseResult<Void> getResult() {
        return new ResponseResult<Void>(this.getStatus(), this.getMessage());
    }

    default ResponseResult<Void> getResult(String message) {
        if (message == null || message.isEmpty()) {
            new ResponseResult<Void>(this.getStatus(), this.getMessage());
        }
        return new ResponseResult<Void>(this.getStatus(), message);
    }

    default <T> ResponseResult<T> getResult(T data) {
        return new ResponseResult<T>(this.getStatus(), this.getMessage(), data);
    }
}
