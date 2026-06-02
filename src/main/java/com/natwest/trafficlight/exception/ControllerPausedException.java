package com.natwest.trafficlight.exception;

public class ControllerPausedException extends RuntimeException{
    public ControllerPausedException(){
        super("Traffic controller is paused");
    }
}
