package org.nhnacademy.family.service;

import java.util.List;
import org.nhnacademy.family.controller.HouseholdRestController;
import org.nhnacademy.family.domain.HouseholdDto;
import org.nhnacademy.family.domain.HouseholdRegisterRequest;
import org.nhnacademy.family.entity.HouseholdMovementAddress;
import org.nhnacademy.family.repository.HouseholdRepository;
import org.springframework.data.repository.query.Param;

public interface HouseholdService {

    void createHousehold(HouseholdRegisterRequest householdRegisterRequest);

    void deleteHousehold(long householdSerialNumber);

    HouseholdDto getHouseholdByHouseholdSerialNumber(long householdSerialNumber);

    HouseholdDto getHouseholdByResident_ResidentSerialNumber(long residentSerialNumber);



}
