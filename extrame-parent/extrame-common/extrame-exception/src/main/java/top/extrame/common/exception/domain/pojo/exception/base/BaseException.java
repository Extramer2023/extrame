package top.extrame.common.exception.domain.pojo.exception.base;


import top.extrame.common.tool.model.Result;

/**
 * BaseException
 *
 * @author jx
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = -2845969923974785031L;
    private final Result result;

    private Object[] args;

    public BaseException(Result result) {
        super(result.getMessage());
        this.result = result;
    }

    public BaseException(Result result, Object[] args) {
        super(result.getMessage());
        this.result = result;
        this.args = args;
    }

    public BaseException(Result result, Throwable cause) {
        super(result.getMessage(), cause);
        this.result = result;
    }

    public BaseException(Result result, Object[] args, Throwable cause) {
        super(result.getMessage(), cause);
        this.result = result;
        this.args = args;
    }

    public Result getResult() {
        return result;
    }

    public Object[] getArgs() {
        return args;
    }
}
