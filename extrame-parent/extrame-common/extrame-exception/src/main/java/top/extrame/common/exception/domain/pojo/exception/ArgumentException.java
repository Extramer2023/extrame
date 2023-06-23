package top.extrame.common.exception.domain.pojo.exception;

import top.extrame.common.exception.domain.module.enums.ResponseEnum;
import top.extrame.common.exception.domain.pojo.exception.base.BaseException;

import java.io.Serializable;

/**
 * ArgumentException
 *
 * @author jx
 */
public class ArgumentException extends BaseException implements Serializable {

    private static final long serialVersionUID = 6449334571737316648L;

    public ArgumentException(ResponseEnum responseEnum, Object[] args) {
        super(responseEnum, args);
    }

    public ArgumentException(ResponseEnum responseEnum, Object[] args, Throwable cause) {
        super(responseEnum, args, cause);
    }
}
