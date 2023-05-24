package org.nhnacademy.family.exception;

public class BirthDeathReportResidentNotFoundException extends RuntimeException {
    private static final String MESSAGE="신고가 존재하지 않습니다. residentSerialNumber : ";
    public BirthDeathReportResidentNotFoundException(long residentSerialNumber, long targetSerialNumber){
        super(MESSAGE + residentSerialNumber + " " + targetSerialNumber);
    }
}