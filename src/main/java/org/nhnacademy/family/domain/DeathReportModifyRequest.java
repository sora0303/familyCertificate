package org.nhnacademy.family.domain;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.nhnacademy.family.enumclass.DeathReportQualificationsCode;

@Data
public class DeathReportModifyRequest {

    @NotNull
    private LocalDate birthDeathReportDate;

    private DeathReportQualificationsCode deathReportQualificationsCode;

    private String emailAddress;

    @NotNull
    @NotBlank
    private String phoneNumber;

}
