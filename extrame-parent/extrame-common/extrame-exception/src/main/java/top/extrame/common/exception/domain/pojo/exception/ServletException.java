package top.extrame.common.exception.domain.pojo.exception;


import top.extrame.common.exception.domain.module.enums.ResponseEnum;
import top.extrame.common.exception.domain.pojo.exception.base.BaseException;

import java.io.Serializable;

/**
 * ServletException
 *
 * @author jx
 */
public class ServletException extends BaseException implements Serializable {

    private static final long serialVersionUID = 6362564704155960407L;

    public ServletException(ResponseEnum responseEnum, Object[] args) {
        super(responseEnum, args);
    }

    public ServletException(ResponseEnum responseEnum, Object[] args, Throwable cause) {
        super(responseEnum, args, cause);
    }
}
