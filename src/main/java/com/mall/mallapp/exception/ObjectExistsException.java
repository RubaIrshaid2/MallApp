package com.mall.mallapp.exception;
/**
 *Exception thrown when an object already exists.
 */
public class ObjectExistsException extends Exception{

    /**
     * Constructs a new ObjectExistsException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the getMessage() method.
     */
    public ObjectExistsException(String s)
    {
        super(s);
    }
}
