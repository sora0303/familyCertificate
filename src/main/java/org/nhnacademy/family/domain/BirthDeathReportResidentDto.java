package org.nhnacademy.family.domain;

import java.time.LocalDate;
import org.nhnacademy.family.entity.Resident;
import org.nhnacademy.family.enumclass.BirthReportQualificationsCode;
import org.nhnacademy.family.enumclass.DeathReportQualificationsCode;

public interface BirthReportResidentDto {
    String getName();

    String getResidentRegistrationNumber();

    LocalDate getBirthDeathReportDate();

    BirthReportQualificationsCode getBirthReportQualificationsCode();

    DeathReportQualificationsCode getDeathReportQualificationsCode();

    String getEmailAddress();

    String getPhoneNumber();


}
