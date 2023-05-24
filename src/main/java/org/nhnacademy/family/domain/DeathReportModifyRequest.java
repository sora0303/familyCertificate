package org.nhnacademy.family.domain;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.nhnacademy.family.enumclass.BirthDeathTypeCode;
import org.nhnacademy.family.enumclass.DeathReportQualificationsCode;

@Data
public class DeathReportRegisterRequest {

    @NotNull
    @NotBlank
    private BirthDeathTypeCode birthDeathTypeCode;

    @NotNull
    private long residentSerialNumber;

    @NotNull
    private long reportResidentSerialNumber;

    @NotNull
    private LocalDate birthDeathReportDate;

    private DeathReportQualificationsCode deathReportQualificationsCode;

    private String emailAddress;

    @NotNull
    @NotBlank
    private String phoneNumber;





}
