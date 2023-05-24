package org.nhnacademy.family.domain;

import java.time.LocalDateTime;

public interface FamilyRelationshipDto {

    String getFamilyRelationshipCode();

    String getName();

    String getResidentRegistrationNumber();

    String getGenderCode();

    LocalDateTime getBirthDate();

}
