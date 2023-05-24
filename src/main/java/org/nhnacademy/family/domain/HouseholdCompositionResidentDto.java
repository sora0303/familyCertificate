package org.nhnacademy.family.domain;

import java.time.LocalDate;

public interface HouseholdCompositionResidentDto {

    String getResidentName();

    String getResidentResidentRegistrationNumber();

    String getHouseholdRelationshipCode();
    LocalDate getReportDate();

    String getHouseholdCompositionChangeReasonCode();

}
