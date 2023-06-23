package top.extrame.common.exception.domain.module.asserts.base;

import org.springframework.lang.Nullable;
import top.extrame.common.exception.domain.pojo.exception.base.BaseException;

import java.util.Collection;

/**
 * The interface Base assert.
 *
 * @param <E> the type parameter
 * @author Jx -zou
 */
public interface BaseAssert<E extends BaseException> {

    /**
     * New exception e.
     *
     * @param args the args
     * @return the e
     */
    E newException(Object... args);

    /**
     * New exception e.
     *
     * @param t    the t
     * @param args the args
     * @return the e
     */
    E newException(Throwable t, Object... args);

    /**
     * Is null.
     *
     * @param obj the obj
     * @throws E the e
     */
    default void isNull(@Nullable Object obj) throws E {
        if (obj == null) {
            throw this.newException();
        }
    }

    /**
     * Is null.
     *
     * @param obj the obj
     * @param t   the t
     * @throws E the e
     */
    default void isNull(@Nullable Object obj, Throwable t) throws E {
        if (obj == null) {
            throw this.newException(t);
        }
    }

    /**
     * Not null.
     *
     * @param obj the obj
     * @throws E the e
     */
    default void notNull(@Nullable Object obj) throws E {
        if (obj != null) {
            throw this.newException();
        }
    }

    /**
     * Not null.
     *
     * @param obj the obj
     * @param t   the t
     * @throws E the e
     */
    default void notNull(@Nullable Object obj, Throwable t) throws E {
        if (obj != null) {
            throw this.newException(t);
        }
    }

    /**
     * Is empty.
     *
     * @param str the str
     * @throws E the e
     */
    default void isEmpty(@Nullable String str) throws E {
        if (str == null || str.isEmpty()) {
            throw this.newException();
        }
    }

    /**
     * Is empty.
     *
     * @param str the str
     * @param t   the t
     * @throws E the e
     */
    default void isEmpty(@Nullable String str, Throwable t) throws E {
        if (str == null || str.isEmpty()) {
            throw this.newException(t);
        }
    }

    /**
     * Not empty.
     *
     * @param str the str
     * @param t   the t
     * @throws E the e
     */
    default void notEmpty(@Nullable String str, Throwable t) throws E {
        if (str == null || str.isEmpty()) {
            throw this.newException(t);
        }
    }

    /**
     * Is blank.
     *
     * @param str the str
     * @throws E the e
     */
    default void isBlank(@Nullable String str) throws E {
        if (str == null || str.trim().isEmpty()) {
            throw this.newException();
        }
    }

    /**
     * Is blank.
     *
     * @param str the str
     * @param t   the t
     * @throws E the e
     */
    default void isBlank(@Nullable String str, Throwable t) throws E {
        if (str == null || str.trim().isEmpty()) {
            throw this.newException(t);
        }
    }

    /**
     * Not blank.
     *
     * @param str the str
     * @throws E the e
     */
    default void notBlank(@Nullable String str) throws E {
        if (str != null && !str.trim().isEmpty()) {
            throw this.newException();
        }
    }

    /**
     * Not blank.
     *
     * @param str the str
     * @param t   the t
     * @throws E the e
     */
    default void notBlank(@Nullable String str, Throwable t) throws E {
        if (str != null && !str.trim().isEmpty()) {
            throw this.newException(t);
        }
    }

    /**
     * Is empty.
     *
     * @param array the array
     * @throws E the e
     */
    default void isEmpty(@Nullable Object[] array) throws E {
        if (array == null || array.length == 0) {
            throw this.newException();
        }
    }

    /**
     * Is empty.
     *
     * @param array the array
     * @param t     the t
     * @throws E the e
     */
    default void isEmpty(@Nullable Object[] array, Throwable t) throws E {
        if (array == null || array.length == 0) {
            throw this.newException(t);
        }
    }

    /**
     * Not empty.
     *
     * @param array the array
     * @throws E the e
     */
    default void notEmpty(@Nullable Object[] array) throws E {
        if (array != null && array.length > 0) {
            throw this.newException();
        }
    }

    /**
     * Not empty.
     *
     * @param array the array
     * @param t     the t
     * @throws E the e
     */
    default void notEmpty(@Nullable Object[] array, Throwable t) throws E {
        if (array != null && array.length > 0) {
            throw this.newException(t);
        }
    }

    /**
     * Is empty.
     *
     * @param collection the collection
     * @throws E the e
     */
    default void isEmpty(@Nullable Collection<?> collection) throws E {
        if (collection == null || collection.size() == 0) {
            throw this.newException();
        }
    }

    /**
     * Is empty.
     *
     * @param collection the collection
     * @param t          the t
     * @throws E the e
     */
    default void isEmpty(@Nullable Collection<?> collection, Throwable t) throws E {
        if (collection == null || collection.size() == 0) {
            throw this.newException(t);
        }
    }

    /**
     * Not empty.
     *
     * @param collection the collection
     * @throws E the e
     */
    default void notEmpty(@Nullable Collection<?> collection) throws E {
        if (collection != null && collection.size() > 0) {
            throw this.newException();
        }
    }

    /**
     * Not empty.
     *
     * @param collection the collection
     * @param t          the t
     * @throws E the e
     */
    default void notEmpty(@Nullable Collection<?> collection, Throwable t) throws E {
        if (collection != null && collection.size() > 0) {
            throw this.newException(t);
        }
    }

    /**
     * Equals ignore case.
     *
     * @param str1 the str 1
     * @param str2 the str 2
     * @throws E the e
     */
    default void equalsIgnoreCase(@Nullable String str1, @Nullable String str2) throws E {
        if (null == str1 || str1.trim().isEmpty() || null == str2 || str2.trim().isEmpty() || !str1.equalsIgnoreCase(str2)) {
            throw this.newException();
        }
    }

    /**
     * Equals ignore case.
     *
     * @param str1 the str 1
     * @param str2 the str 2
     * @param t    the t
     * @throws E the e
     */
    default void equalsIgnoreCase(@Nullable String str1, @Nullable String str2, Throwable t) throws E {
        if (null == str1 || str1.trim().isEmpty() || null == str2 || str2.trim().isEmpty() || !str1.equalsIgnoreCase(str2)) {
            throw this.newException(t);
        }
    }

    /**
     * Equals.
     *
     * @param str1 the str 1
     * @param str2 the str 2
     * @throws E the e
     */
    default void equals(@Nullable String str1, @Nullable String str2) throws E {
        if (null == str1 || str1.trim().isEmpty() || null == str2 || str2.trim().isEmpty() || !str1.equals(str2)) {
            throw this.newException();
        }
    }

    /**
     * Equals.
     *
     * @param str1 the str 1
     * @param str2 the str 2
     * @param t    the t
     * @throws E the e
     */
    default void equals(@Nullable String str1, @Nullable String str2, Throwable t) throws E {
        if (null == str1 || str1.trim().isEmpty() || null == str2 || str2.trim().isEmpty() || !str1.equals(str2)) {
            throw this.newException(t);
        }
    }

    /**
     * Equals.
     *
     * @param obj1 the obj 1
     * @param obj2 the obj 2
     * @throws E the e
     */
    default void equals(@Nullable Object obj1, @Nullable Object obj2) throws E {
        if (null == obj1 || !obj1.equals(obj2)) {
            throw this.newException();
        }
    }

    /**
     * Equals.
     *
     * @param obj1 the obj 1
     * @param obj2 the obj 2
     * @param t    the t
     * @throws E the e
     */
    default void equals(@Nullable Object obj1, @Nullable Object obj2, Throwable t) throws E {
        if (null == obj1 || !obj1.equals(obj2)) {
            throw this.newException(t);
        }
    }

    /**
     * Is instance of.
     *
     * @param type the type
     * @param obj  the obj
     * @throws E the e
     */
    default void isInstanceOf(Class<?> type, @Nullable Object obj) throws E {
        if (null == obj || type.isInstance(obj)) {
            throw this.newException();
        }
    }

    /**
     * Is instance of.
     *
     * @param type the type
     * @param obj  the obj
     * @param t    the t
     * @throws E the e
     */
    default void isInstanceOf(Class<?> type, @Nullable Object obj, Throwable t) throws E {
        if (obj == null || type.isInstance(obj)) {
            throw this.newException(t);
        }
    }

    /**
     * Not instance of.
     *
     * @param type the type
     * @param obj  the obj
     * @throws E the e
     */
    default void notInstanceOf(Class<?> type, @Nullable Object obj) throws E {
        if (!type.isInstance(obj)) {
            throw this.newException();
        }
    }

    /**
     * Not instance of.
     *
     * @param type the type
     * @param obj  the obj
     * @param t    the t
     * @throws E the e
     */
    default void notInstanceOf(Class<?> type, @Nullable Object obj, Throwable t) throws E {
        if (!type.isInstance(obj)) {
            throw this.newException(t);
        }
    }


}
