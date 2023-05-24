package org.nhnacademy.family.exception;

public class ResidentHouseholdException extends RuntimeException {
    private static final String MESSAGE="남은 가족이 있어 삭제가 불가능합니다. residentSerialNumber : ";
    public ResidentHouseholdException(long residentSerialNumber){
        super(MESSAGE + residentSerialNumber);
    }
}