package com.example.individual.garage.common.exceptions;

public class NoTicketIdException extends RuntimeException{
    public NoTicketIdException(String message){
        super(message);
    }
}
