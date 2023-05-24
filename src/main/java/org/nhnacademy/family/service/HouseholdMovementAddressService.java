package org.nhnacademy.family.service;

import java.util.List;
import org.nhnacademy.family.domain.HouseholdMovementAddressDto;
import org.nhnacademy.family.domain.HouseholdMovementAddressModifyRequest;
import org.nhnacademy.family.domain.HouseholdMovementAddressRegisterRequest;
import org.nhnacademy.family.entity.HouseholdMovementAddress;

public interface HouseholdMovementAddressService {

    void createHouseholdMovementAddress(long householdSerialNumber, HouseholdMovementAddressRegisterRequest householdMovementAddressRegisterRequest);

    void modifyHouseholdMovementAddress(long householdSerialNumber, String reportDate, HouseholdMovementAddressModifyRequest householdMovementAddressModifyRequest);

    void deleteHouseholdMovementAddress(long householdSerialNumber, String reportDate);

    List<HouseholdMovementAddressDto> getHouseholdMovementAddresses(long householdSerialNumber);

}
