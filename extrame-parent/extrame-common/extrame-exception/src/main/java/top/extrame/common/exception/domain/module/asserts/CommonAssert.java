package top.extrame.common.exception.domain.module.asserts;


import top.extrame.common.exception.domain.module.asserts.base.BaseAssert;
import top.extrame.common.exception.domain.pojo.exception.CommonException;
import top.extrame.common.tool.model.Result;

/**
 * CommonAssert
 *
 * @author jx
 */
public interface CommonAssert extends BaseAssert<CommonException>, Result {

    @Override
    CommonException newException(Object... args);

    @Override
    CommonException newException(Throwable t, Object... args);

    default void isInsertFailed(int insert) throws CommonException {
        if (insert <= 0) {
            throw this.newException();
        }
    }
}
