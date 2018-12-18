package club.bugmakers.seed.exception;

import lombok.Data;

/**
 * 业务异常类
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Data
public class ServiceException extends RuntimeException{

    private Boolean withNotify;

    public static ServiceException notify(String message) {
        return new ServiceException(message, true);
    }

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Boolean withNotify) {
        super(message);
        this.withNotify = withNotify;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
