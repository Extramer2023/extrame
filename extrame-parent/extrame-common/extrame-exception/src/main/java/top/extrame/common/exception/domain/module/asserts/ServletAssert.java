package top.extrame.common.exception.domain.module.asserts;


import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import top.extrame.common.exception.domain.module.asserts.base.BaseAssert;
import top.extrame.common.exception.domain.pojo.exception.ServletException;
import top.extrame.common.tool.model.Result;

/**
 * ServletAssert
 *
 * @author jx
 */
public interface ServletAssert extends BaseAssert<ServletException>, Result {
    @Override
    ServletException newException(Object... args);

    @Override
    ServletException newException(Throwable t, Object... args);

    default void validateClientId(@Nullable String cid) throws ServletException {
        if (null == cid || cid.length() != 24) {
            throw this.newException();
        }
    }

    default void validateToken(@Nullable String token) throws ServletException {
        if (StringUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
            throw this.newException();
        }
    }
}
