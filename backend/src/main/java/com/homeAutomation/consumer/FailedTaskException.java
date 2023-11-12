package com.homeAutomation.consumer;

public class FailedTaskException extends RuntimeException {
    public FailedTaskException(String message) {
        super(message);
    }
}
