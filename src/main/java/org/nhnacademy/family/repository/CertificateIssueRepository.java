package org.nhnacademy.family.repository;

import java.math.BigDecimal;
import org.nhnacademy.family.domain.CertificateIssueDto;
import org.nhnacademy.family.entity.CertificateIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, BigDecimal> {

    Page<CertificateIssueDto> getCertificateIssuesByResident_ResidentSerialNumberOrderByCertificateIssueDateDesc(Pageable pageable, long residentSerialNumber);

}
