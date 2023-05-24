package org.nhnacademy.family.exception;

import java.time.LocalDate;

public class HouseholdMovementAddressNotFoundException extends RuntimeException {
    private static final String MESSAGE="해당 세대 전입 주소가 존재하지 않습니다.";
    public HouseholdMovementAddressNotFoundException(long householdSerialNumber, LocalDate reportDate){
        super(MESSAGE + householdSerialNumber + " " + reportDate);
    }
}