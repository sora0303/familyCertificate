package org.nhnacademy.family.domain;

import java.time.LocalDate;

public interface HouseholdMovementAddressDto {

    LocalDate getHouseMovementReportDate();

    String getHouseMovementAddress();

    String getLastAddressYn();

}
