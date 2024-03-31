package ru.boro.busapps.exception;

public class NullDataException extends Exception {

    public NullDataException(String message) {
        System.out.println(message);
    }

    public NullDataException(Throwable cause) {

        super(cause);
    }

    public NullDataException(String message, Throwable cause) {

        super(message, cause);
    }

}
