package org.nhnacademy.family.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.nhnacademy.family.entity.BirthDeathReportResident;
import org.springframework.stereotype.Service;


public interface ResidentDto {

    long getResidentSerialNumber();

    String getResidentRegistrationNumber();

    String getName();

    String getGenderCode();

    LocalDateTime getBirthDate();

    String getBirthPlaceCode();

    String getRegistrationBaseAddress();

    LocalDateTime getDeathDate();

    String getDeathPlaceCode();

    String getDeathPlaceAddress();

    List<BirthDeathReportResident> getBirthDeathReportResidents();

}
