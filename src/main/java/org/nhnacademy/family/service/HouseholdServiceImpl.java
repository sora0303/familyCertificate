package org.nhnacademy.family.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.nhnacademy.family.domain.HouseholdDto;
import org.nhnacademy.family.domain.HouseholdRegisterRequest;
import org.nhnacademy.family.entity.Household;
import org.nhnacademy.family.entity.HouseholdCompositionResident;
import org.nhnacademy.family.entity.HouseholdMovementAddress;
import org.nhnacademy.family.entity.Resident;
import org.nhnacademy.family.exception.HouseholdNotFoundException;
import org.nhnacademy.family.exception.ResidentNotFoundException;
import org.nhnacademy.family.repository.HouseholdRepository;
import org.nhnacademy.family.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("householdService")
@RequiredArgsConstructor
public class HouseholdServiceImpl implements HouseholdService{

    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    @Override
    @Transactional
    public void createHousehold(HouseholdRegisterRequest householdRegisterRequest) {
        Household household = new Household();

        Resident resident = residentRepository.findById(householdRegisterRequest.getResidentSerialNumber()).orElseThrow(() -> new ResidentNotFoundException(
            householdRegisterRequest.getResidentSerialNumber()));

        household.setResident(resident);
        household.setHouseholdCompositionDate(householdRegisterRequest.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(householdRegisterRequest.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(householdRegisterRequest.getCurrentHouseMovementAddress());
        List<HouseholdMovementAddress> householdMovementAddressList = new ArrayList<>();
        household.setHouseholdMovementAddresses(householdMovementAddressList);
        List<HouseholdCompositionResident> householdCompositionResidentList = new ArrayList<>();
        household.setHouseholdCompositionResidents(householdCompositionResidentList);

        householdRepository.save(household);
    }

    @Override
    @Transactional
    public void deleteHousehold(long householdSerialNumber) {
        Household household = householdRepository.findById(householdSerialNumber).orElseThrow(() -> new HouseholdNotFoundException(householdSerialNumber));
        householdRepository.delete(household);
    }

    @Override
    public HouseholdDto getHouseholdByHouseholdSerialNumber(long householdSerialNumber) {
        return householdRepository.getHouseholdByHouseholdSerialNumber(householdSerialNumber);
    }

    @Override
    public HouseholdDto getHouseholdByResident_ResidentSerialNumber(long residentSerialNumber) {
        return householdRepository.getHouseholdByResident_ResidentSerialNumber(residentSerialNumber);
    }
}
