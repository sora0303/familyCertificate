package org.nhnacademy.family.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.nhnacademy.family.entity.Resident;
import org.nhnacademy.family.enumclass.CertificateTypeCode;

public interface CertificateIssueDto {

    BigDecimal getCertificateConfirmationNumber();

    CertificateTypeCode getCertificateTypeCode();

    LocalDate getCertificateIssueDate();

    Resident getResident();
}
