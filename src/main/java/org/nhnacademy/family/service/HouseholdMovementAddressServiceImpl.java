package org.nhnacademy.family.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.nhnacademy.family.domain.HouseholdMovementAddressModifyRequest;
import org.nhnacademy.family.domain.HouseholdMovementAddressRegisterRequest;
import org.nhnacademy.family.entity.HouseholdMovementAddress;
import org.nhnacademy.family.repository.HouseholdMovementAddressRepository;
import org.springframework.stereotype.Service;


@Service("householdMovementAddress")
@RequiredArgsConstructor
public class HouseholdMovementAddressSerivceImpl implements HouseholdMovementAddressSerivce {

    HouseholdMovementAddressRepository householdMovementAddressRepository;

    @Override
    public void createHouseholdMovementAddress(HouseholdMovementAddressRegisterRequest householdMovementAddressRegisterRequest) {
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(householdMovementAddressRegisterRequest.getHouseMovementReportDate(), formatter);

        pk.setHouseholdSerialNumber(se);
        pk.setHouseMovementReportDate(householdMovementAddressRegisterRequest.getHouseMovementReportDate().);
    }

    @Override
    public void modifyHouseholdMovementAddress(long householdSerialNumber, LocalDate reportDate,
                                               HouseholdMovementAddressModifyRequest householdMovementAddressModifyRequest) {

    }

    @Override
    public void deleteHouseholdMovementAddress(long householdSerialNumber, LocalDate reportDate) {

    }
}
