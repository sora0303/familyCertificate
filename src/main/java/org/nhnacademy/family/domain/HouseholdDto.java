package org.nhnacademy.family.domain;

import java.time.LocalDate;

public interface HouseholdDto {

    String getResidentName();

    long getResidentResidentSerialNumber();

    long getHouseholdSerialNumber();

    LocalDate getHouseholdCompositionDate();

    String getHouseholdCompositionReasonCode();

    String getCurrentHouseMovementAddress();

}
