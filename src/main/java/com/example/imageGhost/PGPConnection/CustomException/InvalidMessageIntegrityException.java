package com.example.imageGhost.PGPConnection;

public class InvalidMessageIntegrityException extends Exception{
    public InvalidMessageIntegrityException(String message){
        super(message);
    }
}
