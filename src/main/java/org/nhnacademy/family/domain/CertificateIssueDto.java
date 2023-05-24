package org.nhnacademy.family.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.nhnacademy.family.enumclass.CertificateTypeCode;

@Getter
@Setter
@Entity
@Table(name = "certificate_issue")
public class CertificateIssue {

    @Id
    @Column(name = "certificate_confirmation_number")
    private BigDecimal certificateConfirmationNumber;

    @Column(name = "certificate_type_code")
    private CertificateTypeCode certificateTypeCode;

    @Column(name = "certificate_issue_date")
    private LocalDate certificateIssueDate;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;
}
