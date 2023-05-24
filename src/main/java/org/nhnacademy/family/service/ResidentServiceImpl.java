package org.nhnacademy.family.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.nhnacademy.family.domain.HouseholdCompositionResidentDto;
import org.nhnacademy.family.domain.HouseholdDto;
import org.nhnacademy.family.domain.HouseholdSerialNumberDto;
import org.nhnacademy.family.domain.ResidentDto;
import org.nhnacademy.family.domain.ResidentRegisterRequest;
import org.nhnacademy.family.entity.Resident;
import org.nhnacademy.family.exception.ResidentHouseholdException;
import org.nhnacademy.family.exception.ResidentNotFoundException;
import org.nhnacademy.family.repository.HouseholdRepository;
import org.nhnacademy.family.repository.ResidentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("residentService")
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;

    private final HouseholdService householdService;

    private final HouseholdCompositionResidentService householdCompositionResidentService;

    private Resident getResident(long residentSerialNumber) {
        return residentRepository.findById(residentSerialNumber)
            .orElseThrow(() -> new ResidentNotFoundException(residentSerialNumber));
    }

    @Transactional
    @Override
    public void createResident(ResidentRegisterRequest residentRegisterRequest) {
        Resident resident = new Resident();
        resident.setName(residentRegisterRequest.getName());
        resident.setResidentRegistrationNumber(residentRegisterRequest.getResidentRegistrationNumber());
        resident.setGenderCode(residentRegisterRequest.getGenderCode());
        resident.setBirthDate(residentRegisterRequest.getBirthDate());
        resident.setBirthPlaceCode(residentRegisterRequest.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(residentRegisterRequest.getRegistrationBaseAddress());
        resident.setDeathDate(residentRegisterRequest.getDeathDate());
        resident.setDeathPlaceCode(residentRegisterRequest.getDeathPlaceCode());
        resident.setDeathPlaceAddress(residentRegisterRequest.getDeathPlaceAddress());
        residentRepository.save(resident);
    }


    @Transactional
    @Override
    public void modifyResident(long residentSerialNumber, ResidentRegisterRequest residentRegisterRequest) {

        Resident resident = getResident(residentSerialNumber);
        resident.setName(residentRegisterRequest.getName());
        resident.setResidentRegistrationNumber(residentRegisterRequest.getResidentRegistrationNumber());
        resident.setGenderCode(residentRegisterRequest.getGenderCode());
        resident.setBirthDate(residentRegisterRequest.getBirthDate());
        resident.setBirthPlaceCode(residentRegisterRequest.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(residentRegisterRequest.getRegistrationBaseAddress());
        resident.setDeathDate(residentRegisterRequest.getDeathDate());
        resident.setDeathPlaceCode(residentRegisterRequest.getDeathPlaceCode());
        resident.setDeathPlaceAddress(residentRegisterRequest.getDeathPlaceAddress());

        residentRepository.save(resident);
    }

    @Override
    public void deleteResident(long serialNumber) {
        HouseholdDto householdDto = householdService.getHouseholdByResident_ResidentSerialNumber(serialNumber);
        if (Objects.nonNull(householdDto)) {
            if (serialNumber == householdDto.getResidentResidentSerialNumber()) {
                long householdSerialNumber = householdDto.getHouseholdSerialNumber();
                List<HouseholdCompositionResidentDto> householdCompositionResidentDtos =
                    householdCompositionResidentService.findHouseholdCompositionResidentByHousehold_HouseholdSerialNumber(
                        householdSerialNumber);
                if (householdCompositionResidentDtos.size() > 1) {
                    throw new ResidentHouseholdException(serialNumber);
                }
            }
        }
        residentRepository.delete(getResident(serialNumber));
    }

    @Override
    public Page<ResidentDto> getResidents(Pageable pageable) {
        return residentRepository.getAllBy(pageable);
    }

    @Override
    public ResidentDto getResidentByResidentSerialNumber(long residentSerialNumber) {
        return residentRepository.getResidentByResidentSerialNumber(residentSerialNumber);
    }
}
