package com.example.lobby.Exceptions;

import lombok.Data;


public class RoomFullException extends RuntimeException{
    // Default constructor
    public RoomFullException() {
        super("The room is already full.");
    }

    // Constructor with custom message
    public RoomFullException(String message) {
        super(message);
    }

    // Constructor with custom message and cause
    public RoomFullException(String message, Throwable cause) {
        super(message, cause);
    }
}
