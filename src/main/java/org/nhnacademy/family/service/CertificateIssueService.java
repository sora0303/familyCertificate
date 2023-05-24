package org.nhnacademy.family.service;

import org.nhnacademy.family.domain.CertificateIssueClassDto;
import org.nhnacademy.family.domain.CertificateIssueDto;
import org.nhnacademy.family.entity.CertificateIssue;
import org.nhnacademy.family.enumclass.CertificateTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertificateIssueService {

    CertificateIssueClassDto createCertificateIssue(long residentSerialNumber, CertificateTypeCode certificateTypeCode);

    Page<CertificateIssueDto> getCertificateIssuesByResidentSerialNumber(Pageable pageable, long residentSerialNumber);

}
