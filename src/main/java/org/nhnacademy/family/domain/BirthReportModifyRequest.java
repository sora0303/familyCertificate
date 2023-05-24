package org.nhnacademy.family.domain;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.nhnacademy.family.enumclass.BirthReportQualificationsCode;

@Data
public class BirthReportModifyRequest {

    @NotNull
    private LocalDate birthDeathReportDate;

    private BirthReportQualificationsCode birthReportQualificationsCode;

    private String emailAddress;

    @NotNull
    @NotBlank
    private String phoneNumber;

}
