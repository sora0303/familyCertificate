package org.nhnacademy.family.exception;

public class HouseholdNotFoundException extends RuntimeException {
    private static final String MESSAGE="세대가 존재하지 않습니다. householdSerialNumber : ";
    public HouseholdNotFoundException(long householdSerialNumber){
        super(MESSAGE + householdSerialNumber);
    }
}