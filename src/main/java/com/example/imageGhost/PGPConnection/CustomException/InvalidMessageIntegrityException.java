package com.example.imageGhost.PGPConnection.CustomException;

public class InvalidMessageIntegrityException extends Exception{
    public InvalidMessageIntegrityException(String message){
        super(message);
    }
}
