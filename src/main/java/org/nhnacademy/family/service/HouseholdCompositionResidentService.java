package org.nhnacademy.family.service;

import java.util.List;
import org.nhnacademy.family.domain.HouseholdCompositionResidentDto;
import org.nhnacademy.family.domain.HouseholdSerialNumberDto;

public interface HouseholdCompositionResidentService {
    HouseholdSerialNumberDto findHouseholdCompositionResidentByResident_ResidentSerialNumber(long residentSerialNumber);

    List<HouseholdCompositionResidentDto> findHouseholdCompositionResidentByHousehold_HouseholdSerialNumber(long householdSerialNumber);


}
