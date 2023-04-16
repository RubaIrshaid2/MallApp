/**
 * Exception thrown when a resource is not found.
 */
package com.mall.mallapp.exception;

public class NotFoundException extends RuntimeException{

    /**
     * Constructs a new NotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public NotFoundException(String s)
    {
        super(s);
    }
}
