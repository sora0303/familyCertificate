package org.nhnacademy.family.repository;

import org.nhnacademy.family.domain.BirthDeathReportResidentDto;
import org.nhnacademy.family.entity.BirthDeathReportResident;
import org.nhnacademy.family.enumclass.BirthDeathTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {


    @Query("SELECT r2.name AS name, r2.residentRegistrationNumber AS residentRegistrationNumber, " +
        "b.birthDeathReportDate AS birthDeathReportDate, b.birthReportQualificationsCode AS birthReportQualificationsCode, b.deathReportQualificationsCode AS deathReportQualificationsCode, " +
        "b.emailAddress AS emailAddress, b.phoneNumber AS phoneNumber " +
        "FROM BirthDeathReportResident b " +
        "INNER JOIN b.resident r1 " +
        "INNER JOIN b.residentReport r2 " +
        "WHERE b.pk.birthDeathTypeCode = :birthDeathTypeCode AND r1.residentSerialNumber = :residentSerialNumber")
    BirthDeathReportResidentDto findBirthReportResidentByResidentSerialNumber(@Param("residentSerialNumber") Long residentSerialNumber,
                                                                              @Param("birthDeathTypeCode")
                                                                         BirthDeathTypeCode birthDeathTypeCode);


}

