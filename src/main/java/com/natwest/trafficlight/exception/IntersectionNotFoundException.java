package com.natwest.trafficlight.exception;

public class IntersectionNotFoundException extends RuntimeException{
    public IntersectionNotFoundException(String intersectionId){
        super("Intersection Not Found" + intersectionId);
    }
}
