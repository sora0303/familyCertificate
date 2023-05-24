package org.nhnacademy.family.domain;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BirthReportRegisterRequest {

    @NotNull
    @NotBlank
    private String birthDeathTypeCode;

    @NotNull
    private long residentSerialNumber;

    @NotNull
    private long reportResidentSerialNumber;

    @NotNull
    private LocalDate birthDeathReportDate;

    private String birthReportQualificationsCode;

    private String emailAddress;

    @NotNull
    @NotBlank
    private String phoneNumber;





}
