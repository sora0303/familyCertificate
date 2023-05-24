package org.nhnacademy.family.exception;

public class FamilyRelationshipNotFoundException extends RuntimeException {
    private static final String MESSAGE="해당 가족관계가 존재하지 않습니다.";
    public FamilyRelationshipNotFoundException(long residentSerialNumber, long familySerialNumber){
        super(MESSAGE + residentSerialNumber + " " + familySerialNumber);
    }
}