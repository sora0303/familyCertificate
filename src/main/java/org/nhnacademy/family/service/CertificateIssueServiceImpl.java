package org.nhnacademy.family.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.nhnacademy.family.domain.CertificateIssueClassDto;
import org.nhnacademy.family.domain.CertificateIssueDto;
import org.nhnacademy.family.entity.CertificateIssue;
import org.nhnacademy.family.entity.Resident;
import org.nhnacademy.family.enumclass.CertificateTypeCode;
import org.nhnacademy.family.exception.ResidentNotFoundException;
import org.nhnacademy.family.repository.CertificateIssueRepository;
import org.nhnacademy.family.repository.ResidentRepository;
import org.nhnacademy.family.utils.RandomNumberGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("certificateIssueService")
@RequiredArgsConstructor
public class CertificateIssueServiceImpl implements CertificateIssueService{

    private final CertificateIssueRepository certificateIssueRepository;
    private final ResidentRepository residentRepository;

    @Override
    public Page<CertificateIssueDto> getCertificateIssuesByResidentSerialNumber(Pageable pageable, long residentSerialNumber) {
        return certificateIssueRepository.getCertificateIssuesByResident_ResidentSerialNumberOrderByCertificateIssueDateDesc(pageable, residentSerialNumber);
    }

    @Override
    @Transactional
    public CertificateIssueClassDto createCertificateIssue(long residentSerialNumber, CertificateTypeCode certificateTypeCode) {

        CertificateIssue certificateIssue = new CertificateIssue();
        certificateIssue.setCertificateConfirmationNumber(RandomNumberGenerator.generateRandomNumber(16));
        certificateIssue.setCertificateTypeCode(certificateTypeCode);
        certificateIssue.setCertificateIssueDate(LocalDate.now());

        Resident resident = residentRepository.findById(residentSerialNumber).orElseThrow(() -> new ResidentNotFoundException(residentSerialNumber));
        certificateIssue.setResident(resident);

        certificateIssueRepository.save(certificateIssue);

        CertificateIssueClassDto certificateIssueClassDto = new CertificateIssueClassDto(certificateIssue);

       return certificateIssueClassDto;
    }
}
