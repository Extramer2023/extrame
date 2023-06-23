package top.extrame.common.exception.domain.module.asserts;

import top.extrame.common.exception.domain.module.asserts.base.BaseAssert;
import top.extrame.common.exception.domain.pojo.exception.ArgumentException;

/**
 * 参数维护
 * The interface Argument assert.
 *
 * @author Jx-zou
 */
public interface ArgumentAssert extends BaseAssert<ArgumentException> {

    @Override
    ArgumentException newException(Object... args);

    @Override
    ArgumentException newException(Throwable t, Object... args);
}
