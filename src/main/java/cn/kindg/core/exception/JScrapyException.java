package cn.kindg.core.exception;

/**
 * 运行时异常捕获，统一处理
 */
public class JScrapyException extends RuntimeException{
    static final long serialVersionUID = 1L;

    public JScrapyException(String message) {
        super(message);
    }

    public JScrapyException(String message, Throwable cause) {
        super(message, cause);
    }

    public JScrapyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
