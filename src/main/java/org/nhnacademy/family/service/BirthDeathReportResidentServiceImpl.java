package org.nhnacademy.family.service;

import lombok.RequiredArgsConstructor;
import org.nhnacademy.family.domain.BirthReportModifyRequest;
import org.nhnacademy.family.domain.BirthReportRegisterRequest;
import org.nhnacademy.family.domain.BirthDeathReportResidentDto;
import org.nhnacademy.family.domain.DeathReportModifyRequest;
import org.nhnacademy.family.domain.DeathReportRegisterRequest;
import org.nhnacademy.family.entity.BirthDeathReportResident;
import org.nhnacademy.family.entity.Resident;
import org.nhnacademy.family.enumclass.BirthDeathTypeCode;
import org.nhnacademy.family.exception.BirthDeathReportResidentNotFoundException;
import org.nhnacademy.family.exception.ResidentNotFoundException;
import org.nhnacademy.family.repository.BirthDeathReportResidentRepository;
import org.nhnacademy.family.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("BirthDeathReportResidentService")
@RequiredArgsConstructor
public class BirthDeathReportResidentServiceImpl implements BirthDeathReportResidentService{

    private final ResidentRepository residentRepository;

    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;

    private BirthDeathReportResident getBirthReportResident(long serialNumber, long targetSerialNumber) {

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode(BirthDeathTypeCode.출생);
        pk.setReportResidentSerialNumber(serialNumber);
        pk.setResidentSerialNumber(targetSerialNumber);

        return birthDeathReportResidentRepository.findById(pk).orElseThrow(() -> new BirthDeathReportResidentNotFoundException(serialNumber, targetSerialNumber));
    }

    @Override
    @Transactional
    public void createBirthReportResident(long serialNumber,
                                                              BirthReportRegisterRequest birthReportRegisterRequest) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode(BirthDeathTypeCode.출생);
        pk.setReportResidentSerialNumber(serialNumber);
        pk.setResidentSerialNumber(birthReportRegisterRequest.getResidentSerialNumber());

        Resident resident = residentRepository.findById(birthReportRegisterRequest.getResidentSerialNumber()).orElseThrow(() -> new ResidentNotFoundException(
            birthReportRegisterRequest.getResidentSerialNumber()));
        Resident residentReport = residentRepository.findById(serialNumber).orElseThrow(() -> new ResidentNotFoundException(serialNumber));

        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setResident(resident);
        birthDeathReportResident.setResidentReport(residentReport);
        birthDeathReportResident.setBirthDeathReportDate(birthReportRegisterRequest.getBirthDeathReportDate());
        birthDeathReportResident.setBirthReportQualificationsCode(birthReportRegisterRequest.getBirthReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(birthReportRegisterRequest.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(birthReportRegisterRequest.getPhoneNumber());

        birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    @Transactional
    public void modifyBirthReportResident(long serialNumber, long targetSerialNumber,
                                                              BirthReportModifyRequest birthReportModifyRequest) {

        BirthDeathReportResident birthDeathReportResident = getBirthReportResident(serialNumber, targetSerialNumber);

        birthDeathReportResident.setBirthDeathReportDate(birthReportModifyRequest.getBirthDeathReportDate());
        birthDeathReportResident.setBirthReportQualificationsCode(birthReportModifyRequest.getBirthReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(birthReportModifyRequest.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(birthReportModifyRequest.getPhoneNumber());

        birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    @Transactional
    public void deleteBirthReportResident(long serialNumber, long targetSerialNumber) {
        BirthDeathReportResident birthDeathReportResident = getBirthReportResident(serialNumber, targetSerialNumber);
        birthDeathReportResidentRepository.delete(birthDeathReportResident);
    }


    private BirthDeathReportResident getDeathReportResident(long serialNumber, long targetSerialNumber) {

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode(BirthDeathTypeCode.사망);
        pk.setReportResidentSerialNumber(serialNumber);
        pk.setResidentSerialNumber(targetSerialNumber);

        return birthDeathReportResidentRepository.findById(pk).orElseThrow(() -> new BirthDeathReportResidentNotFoundException(serialNumber, targetSerialNumber));

    }

    @Override
    @Transactional
    public void createDeathReportResident(long serialNumber, DeathReportRegisterRequest deathReportRegisterRequest) {
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode(BirthDeathTypeCode.사망);
        pk.setReportResidentSerialNumber(serialNumber);
        pk.setResidentSerialNumber(deathReportRegisterRequest.getResidentSerialNumber());

        Resident resident = residentRepository.findById(deathReportRegisterRequest.getResidentSerialNumber()).orElseThrow(() -> new ResidentNotFoundException(
            deathReportRegisterRequest.getResidentSerialNumber()));
        Resident residentReport = residentRepository.findById(serialNumber).orElseThrow(() -> new ResidentNotFoundException(serialNumber));

        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setResident(resident);
        birthDeathReportResident.setResidentReport(residentReport);
        birthDeathReportResident.setBirthDeathReportDate(deathReportRegisterRequest.getBirthDeathReportDate());
        birthDeathReportResident.setDeathReportQualificationsCode(deathReportRegisterRequest.getDeathReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(deathReportRegisterRequest.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(deathReportRegisterRequest.getPhoneNumber());

        birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    @Transactional
    public void modifyDeathReportResident(long serialNumber, long targetSerialNumber,
                                                              DeathReportModifyRequest deathReportModifyRequest) {
        BirthDeathReportResident birthDeathReportResident = getDeathReportResident(serialNumber, targetSerialNumber);

        birthDeathReportResident.setBirthDeathReportDate(deathReportModifyRequest.getBirthDeathReportDate());
        birthDeathReportResident.setDeathReportQualificationsCode(deathReportModifyRequest.getDeathReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(deathReportModifyRequest.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(deathReportModifyRequest.getPhoneNumber());

        birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    @Transactional
    public void deleteDeathReportResident(long serialNumber, long targetSerialNumber) {
        BirthDeathReportResident birthDeathReportResident = getDeathReportResident(serialNumber, targetSerialNumber);
        birthDeathReportResidentRepository.delete(birthDeathReportResident);
    }

    @Override
    public BirthDeathReportResidentDto findBirthReportResidentByResidentSerialNumber(long residentSerialNumber,
                                                                                     BirthDeathTypeCode birthDeathTypeCode) {
        return birthDeathReportResidentRepository.findBirthReportResidentByResidentSerialNumber(residentSerialNumber, birthDeathTypeCode);
    }
}
