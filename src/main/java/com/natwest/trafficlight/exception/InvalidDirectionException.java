package com.natwest.trafficlight.exception;

public class InvalidDirectionException extends  RuntimeException{
    public InvalidDirectionException(String direction){
        super("Inavlid direction" + direction);
    }
}
