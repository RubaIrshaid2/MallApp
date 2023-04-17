package com.mall.mallapp.exception;
/**
 * Exception thrown when a resource is not found.
 */
public class NotFoundException extends Exception{

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
