package top.extrame.common.exception.domain.module.asserts;


import org.apache.commons.lang3.StringUtils;
import top.extrame.common.exception.domain.module.asserts.base.BaseAssert;
import top.extrame.common.exception.domain.pojo.exception.BusinessException;
import top.extrame.common.tool.util.ValidatorUtils;

/**
 * BusinessAssert
 *
 * @author jx
 */
public interface BusinessAssert extends BaseAssert<BusinessException> {

    @Override
    BusinessException newException(Object... args);

    @Override
    BusinessException newException(Throwable t, Object... args);

    default boolean validateSql(String param) {
        return StringUtils.isNotBlank(param) && !param.startsWith("or") && !param.startsWith("and") && !param.contains(";");
    }

    default void validateAccount(String account) throws BusinessException {
        if (StringUtils.isBlank(account) || account.length() != 32) {
            throw this.newException();
        }
    }

    default void validateCaptcha(String captcha, int size) throws BusinessException {
        if (StringUtils.isBlank(captcha) || captcha.length() != size || !ValidatorUtils.isMailCaptcha(captcha)) {
            throw this.newException();
        }
    }

    default void validateMail(String mail) throws BusinessException {
        if (StringUtils.isBlank(mail) || !ValidatorUtils.isEmail(mail)) {
            throw this.newException();
        }
    }

    default void validatePassword(String password) throws BusinessException {
        if (StringUtils.isBlank(password) || !ValidatorUtils.isPassword(password)) {
            throw this.newException();
        }
    }

    default void validateDescription(String desc) throws BusinessException {
        if (StringUtils.isNotBlank(desc) && !ValidatorUtils.isDesc(desc)) {
            throw this.newException();
        }
    }

    default void validateNickname(String nickname) throws BusinessException {
        if (StringUtils.isNotBlank(nickname) && !ValidatorUtils.isNickname(nickname)) {
            throw this.newException();
        }
    }
}
