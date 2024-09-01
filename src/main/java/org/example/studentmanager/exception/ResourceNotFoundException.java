package org.example.studentmanager.exception;

/**
 * @author vero-git-hub
 **/
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
