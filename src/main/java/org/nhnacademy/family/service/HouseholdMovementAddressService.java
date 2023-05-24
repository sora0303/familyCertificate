package org.nhnacademy.family.service;

import java.time.LocalDate;
import org.nhnacademy.family.domain.HouseholdMovementAddressModifyRequest;
import org.nhnacademy.family.domain.HouseholdMovementAddressRegisterRequest;

public interface HouseholdMovementAddressSerivce {

    void createHouseholdMovementAddress(long householdSerialNumber, HouseholdMovementAddressRegisterRequest householdMovementAddressRegisterRequest);

    void modifyHouseholdMovementAddress(long householdSerialNumber, LocalDate reportDate, HouseholdMovementAddressModifyRequest householdMovementAddressModifyRequest);

    void deleteHouseholdMovementAddress(long householdSerialNumber, LocalDate reportDate);
}
