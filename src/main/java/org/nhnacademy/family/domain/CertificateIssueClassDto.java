package org.nhnacademy.family.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import org.nhnacademy.family.entity.CertificateIssue;
import org.nhnacademy.family.entity.Resident;
import org.nhnacademy.family.enumclass.CertificateTypeCode;
import org.springframework.stereotype.Service;

@Getter
public class CertificateIssueClassDto {

    private BigDecimal certificateConfirmationNumber;

    private CertificateTypeCode certificateTypeCode;

    private LocalDate certificateIssueDate;

    public CertificateIssueClassDto(CertificateIssue certificateIssue){
        this.certificateConfirmationNumber = certificateIssue.getCertificateConfirmationNumber();
        this.certificateTypeCode = certificateIssue.getCertificateTypeCode();
        this.certificateIssueDate = certificateIssue.getCertificateIssueDate();

    }

}
