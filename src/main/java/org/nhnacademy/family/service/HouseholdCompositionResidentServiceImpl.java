package org.nhnacademy.family.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.nhnacademy.family.domain.HouseholdCompositionResidentDto;
import org.nhnacademy.family.domain.HouseholdSerialNumberDto;
import org.nhnacademy.family.repository.HouseholdCompositionResidentRepository;
import org.nhnacademy.family.repository.HouseholdRepository;
import org.nhnacademy.family.repository.ResidentRepository;
import org.springframework.stereotype.Service;

@Service("householdCompositionResidentService")
@RequiredArgsConstructor
public class HouseholdCompositionResidentServiceImpl implements HouseholdCompositionResidentService{

   private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    @Override
    public HouseholdSerialNumberDto findHouseholdCompositionResidentByResident_ResidentSerialNumber(
        long residentSerialNumber) {
        return householdCompositionResidentRepository.findHouseholdCompositionResidentByResident_ResidentSerialNumber(residentSerialNumber);
    }

    @Override
    public List<HouseholdCompositionResidentDto> findHouseholdCompositionResidentByHousehold_HouseholdSerialNumber(
        long householdSerialNumber) {
        return householdCompositionResidentRepository.findHouseholdCompositionResidentByHousehold_HouseholdSerialNumber(householdSerialNumber);
    }
}
