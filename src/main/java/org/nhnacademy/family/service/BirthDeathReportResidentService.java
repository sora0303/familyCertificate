package org.nhnacademy.family.service;

import org.nhnacademy.family.domain.BirthReportModifyRequest;
import org.nhnacademy.family.domain.BirthReportRegisterRequest;
import org.nhnacademy.family.domain.BirthDeathReportResidentDto;
import org.nhnacademy.family.domain.DeathReportModifyRequest;
import org.nhnacademy.family.domain.DeathReportRegisterRequest;
import org.nhnacademy.family.enumclass.BirthDeathTypeCode;

public interface BirthDeathReportResidentService {


    void createBirthReportResident(long serialNumber, BirthReportRegisterRequest birthReportRegisterRequest);

    void modifyBirthReportResident(long serialNumber, long targetSerialNumber, BirthReportModifyRequest birthReportModifyRequest);

    void deleteBirthReportResident(long serialNumber, long targetSerialNumber);


    void createDeathReportResident(long serialNumber, DeathReportRegisterRequest deathReportRegisterRequest);


    void modifyDeathReportResident(long serialNumber, long targetSerialNumber, DeathReportModifyRequest birthReportModifyRequest);

    void deleteDeathReportResident(long serialNumber, long targetSerialNumber);


    BirthDeathReportResidentDto findBirthReportResidentByResidentSerialNumber(long residentSerialNumber, BirthDeathTypeCode birthDeathTypeCode);

}
