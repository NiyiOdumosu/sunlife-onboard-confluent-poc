package org.sunlife.confluent.sunlifepoc.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}