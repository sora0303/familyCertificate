package org.nhnacademy.family.exception;

public class ResidentNotFoundException extends RuntimeException {
    private static final String MESSAGE="주민이 존재하지 않습니다. residentSerialNumber : ";
    public ResidentNotFoundException(long residentSerialNumber){
        super(MESSAGE + residentSerialNumber);
    }
}