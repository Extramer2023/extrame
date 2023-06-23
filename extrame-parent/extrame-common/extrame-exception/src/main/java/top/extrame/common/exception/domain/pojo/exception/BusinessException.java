package top.extrame.common.exception.domain.pojo.exception;


import top.extrame.common.exception.domain.module.enums.ResponseEnum;
import top.extrame.common.exception.domain.pojo.exception.base.BaseException;

import java.io.Serializable;


public class BusinessException extends BaseException implements Serializable {

    private static final long serialVersionUID = 7373277090924206609L;

    public BusinessException(ResponseEnum responseEnum, Object[] args) {
        super(responseEnum, args);
    }

    public BusinessException(ResponseEnum responseEnum, Object[] args, Throwable cause) {
        super(responseEnum, args, cause);
    }
}
