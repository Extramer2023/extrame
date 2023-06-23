package top.extrame.common.exception.enums;


import top.extrame.common.exception.domain.module.asserts.ArgumentAssert;
import top.extrame.common.exception.domain.module.enums.ResponseEnum;
import top.extrame.common.exception.domain.pojo.exception.ArgumentException;

/**
 * ArgumentResponseEnum
 *
 * @author jx
 */
public enum ArgumentResponse implements ResponseEnum, ArgumentAssert {

    VALID_ERROR(5001,"参数错误"),
    CAPTCHA_ERROR(5002,"验证码错误");

    private final Integer status;
    private final String message;

    ArgumentResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public ArgumentException newException(Object... args) {
        return new ArgumentException(this, args);
    }

    @Override
    public ArgumentException newException(Throwable t, Object... args) {
        return new ArgumentException(this, args, t);
    }

    @Override
    public Integer getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
