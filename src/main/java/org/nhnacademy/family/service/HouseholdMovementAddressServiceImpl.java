package org.nhnacademy.family.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.nhnacademy.family.domain.HouseholdMovementAddressDto;
import org.nhnacademy.family.domain.HouseholdMovementAddressModifyRequest;
import org.nhnacademy.family.domain.HouseholdMovementAddressRegisterRequest;
import org.nhnacademy.family.entity.Household;
import org.nhnacademy.family.entity.HouseholdMovementAddress;
import org.nhnacademy.family.exception.HouseholdMovementAddressNotFoundException;
import org.nhnacademy.family.exception.HouseholdNotFoundException;
import org.nhnacademy.family.repository.HouseholdMovementAddressRepository;
import org.nhnacademy.family.repository.HouseholdRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("householdMovementAddress")
@RequiredArgsConstructor
public class HouseholdMovementAddressServiceImpl implements HouseholdMovementAddressService {

    private final HouseholdMovementAddressRepository householdMovementAddressRepository;

    private final HouseholdRepository householdRepository;

    private HouseholdMovementAddress getHouseholdMovementAddress(long householdSerialNumber, String reportDate){

        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(reportDate, formatter);
        pk.setHouseMovementReportDate(date);
        pk.setHouseholdSerialNumber(householdSerialNumber);

        return householdMovementAddressRepository.findById(pk).orElseThrow(() -> new HouseholdMovementAddressNotFoundException(householdSerialNumber, date));
    }

    @Override
    @Transactional
    public void createHouseholdMovementAddress(long householdSerialNumber, HouseholdMovementAddressRegisterRequest householdMovementAddressRegisterRequest) {
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(householdMovementAddressRegisterRequest.getHouseMovementReportDate(), formatter);

        pk.setHouseholdSerialNumber(householdSerialNumber);
        pk.setHouseMovementReportDate(date);

        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        householdMovementAddress.setPk(pk);
        householdMovementAddress.setHouseMovementAddress(householdMovementAddressRegisterRequest.getHouseholdMovementAddress());
        householdMovementAddress.setLastAddressYn(householdMovementAddressRegisterRequest.getLastAddressYn());

        Household household = householdRepository.findById(householdSerialNumber).orElseThrow(() -> new HouseholdNotFoundException(householdSerialNumber));
        householdMovementAddress.setHousehold(household);

        householdMovementAddressRepository.save(householdMovementAddress);
    }

    @Override
    @Transactional
    public void modifyHouseholdMovementAddress(long householdSerialNumber, String reportDate,
                                               HouseholdMovementAddressModifyRequest householdMovementAddressModifyRequest) {

        HouseholdMovementAddress householdMovementAddress = getHouseholdMovementAddress(householdSerialNumber, reportDate);
        householdMovementAddress.setHouseMovementAddress(householdMovementAddressModifyRequest.getHouseholdMovementAddress());
        householdMovementAddress.setLastAddressYn(householdMovementAddressModifyRequest.getLastAddressYn());

        householdMovementAddressRepository.save(householdMovementAddress);
    }

    @Override
    @Transactional
    public void deleteHouseholdMovementAddress(long householdSerialNumber, String reportDate) {

        HouseholdMovementAddress householdMovementAddress = getHouseholdMovementAddress(householdSerialNumber, reportDate);
        householdMovementAddressRepository.delete(householdMovementAddress);
    }

    @Override
    public List<HouseholdMovementAddressDto> getHouseholdMovementAddresses(long householdSerialNumber) {
        return householdMovementAddressRepository.getHouseholdMovementAddresses(householdSerialNumber);
    }
}
