package org.nhnacademy.family.service;


import java.lang.reflect.Member;
import java.util.List;
import org.nhnacademy.family.domain.ResidentDto;
import org.nhnacademy.family.domain.ResidentRegisterRequest;
import org.nhnacademy.family.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentService {


    void createResident(ResidentRegisterRequest residentRegisterRequest);

    void modifyResident(long serialNumber, ResidentRegisterRequest residentRegisterRequest);

    void deleteResident(long serialNumber);
    Page<ResidentDto> getResidents(Pageable pageable);

    ResidentDto getResidentByResidentSerialNumber(long residentSerialNumber);
}
