package org.nhnacademy.family.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseholdMovementAddressRegisterRequest {

    private LocalDate houseMovementReportDate;

    private long householdSerialNumber;

    private String householdMovementAddress;

    private String lastAddressYn;

}
