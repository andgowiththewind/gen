package com.gust.cafe.gen.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义业务异常
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GenServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;

    public GenServiceException(String message) {
        super(message);
        this.message = message;
    }


    /**
     * 调用此方法运行的代码块，如果有异常则抛出指定的自定义异常{@link GenServiceException}
     */
    public static void wrapper(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            throw new GenServiceException(e.getMessage());
        }
    }

}
