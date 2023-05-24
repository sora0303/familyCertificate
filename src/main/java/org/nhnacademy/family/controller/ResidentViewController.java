package org.nhnacademy.family.controller;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhnacademy.family.domain.BirthDeathReportResidentDto;
import org.nhnacademy.family.domain.CertificateIssueClassDto;
import org.nhnacademy.family.domain.CertificateIssueDto;
import org.nhnacademy.family.domain.FamilyRelationshipDto;
import org.nhnacademy.family.domain.HouseholdCompositionResidentDto;
import org.nhnacademy.family.domain.HouseholdDto;
import org.nhnacademy.family.domain.HouseholdMovementAddressDto;
import org.nhnacademy.family.domain.HouseholdSerialNumberDto;
import org.nhnacademy.family.domain.ResidentDto;
import org.nhnacademy.family.enumclass.BirthDeathTypeCode;
import org.nhnacademy.family.enumclass.CertificateTypeCode;
import org.nhnacademy.family.exception.HouseholdNotFoundException;
import org.nhnacademy.family.service.BirthDeathReportResidentService;
import org.nhnacademy.family.service.CertificateIssueService;
import org.nhnacademy.family.service.FamilyRelationshipService;
import org.nhnacademy.family.service.HouseholdCompositionResidentService;
import org.nhnacademy.family.service.HouseholdMovementAddressService;
import org.nhnacademy.family.service.HouseholdService;
import org.nhnacademy.family.service.ResidentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ResidentViewController {

    private final ResidentService residentService;

    private final BirthDeathReportResidentService birthDeathReportResidentService;

    private final FamilyRelationshipService familyRelationshipService;

    private final CertificateIssueService certificateIssueService;

    private final HouseholdCompositionResidentService householdCompositionResidentService;

    private final HouseholdService householdService;

    private final HouseholdMovementAddressService householdMovementAddressService;

    //모든 주민 목록 조회
    @GetMapping("/residents")
    public String getResidents(@PageableDefault(size = 5) Pageable pageable, Model model, HttpServletRequest request, HttpServletResponse response) {
        Page<ResidentDto> residentDtos = residentService.getResidents(pageable);
        model.addAttribute("residentDtos", residentDtos);

        log.info("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
            residentDtos.getTotalElements(), residentDtos.getTotalPages(), residentDtos.getSize(),
            residentDtos.getNumber(), residentDtos.getNumberOfElements());

        return "residents";
    }

    //가족관계증명서 조회
    @GetMapping("/residents/{residentSerialNumber}/family-relationship-certificate")
    public String getFamilyRelationshipCertificate(@PathVariable("residentSerialNumber") long residentSerialNumber, Model model){

        CertificateIssueClassDto certificateIssueClassDto = certificateIssueService.createCertificateIssue(residentSerialNumber, CertificateTypeCode.가족관계증명서);
        model.addAttribute("certificateIssue", certificateIssueClassDto);

        ResidentDto residentDto = residentService.getResidentByResidentSerialNumber(residentSerialNumber);
        model.addAttribute("resident", residentDto);

        List<FamilyRelationshipDto> familyRelationshipDtos = familyRelationshipService.findAllByResidentBase_ResidentSerialNumber(residentSerialNumber);
        model.addAttribute("familyRelaionshipDtos", familyRelationshipDtos);

        return "familyrelationshipcertificate";
    }

    //주민등록등본 조회
    @GetMapping("/residents/{residentSerialNumber}/resident-registration-certificate")
    public String getResidentRegistrationCertificate(@PathVariable("residentSerialNumber") long residentSerialNumber, Model model){


        HouseholdSerialNumberDto householdSerialNumberDto = householdCompositionResidentService.findHouseholdCompositionResidentByResident_ResidentSerialNumber(residentSerialNumber);
        if(Objects.nonNull(householdSerialNumberDto)){

            CertificateIssueClassDto certificateIssueClassDto = certificateIssueService.createCertificateIssue(residentSerialNumber, CertificateTypeCode.주민등록등본);
            model.addAttribute("certificateIssue", certificateIssueClassDto);

            long householdSerialNumber = householdSerialNumberDto.getHouseholdHouseholdSerialNumber();

            HouseholdDto householdDto = householdService.getHouseholdByHouseholdSerialNumber(householdSerialNumber);
            model.addAttribute("household", householdDto);

            List<HouseholdMovementAddressDto> householdMovementAddressDtos = householdMovementAddressService.getHouseholdMovementAddresses(householdSerialNumber);
            model.addAttribute("householdMovementAddresses", householdMovementAddressDtos);

            List<HouseholdCompositionResidentDto> householdCompositionResidentDtos = householdCompositionResidentService.findHouseholdCompositionResidentByHousehold_HouseholdSerialNumber(householdSerialNumber);
            model.addAttribute("householdCompositionResidents", householdCompositionResidentDtos);
        } else {
            throw new HouseholdNotFoundException(0);
        }


        return "residentregistrationcertificate";
    }

    //출생신고서 조회
    @GetMapping("/residents/{residentSerialNumber}/birth-report")
    public String getBirthReportCertificate(@PathVariable("residentSerialNumber") long residentSerialNumber, Model model){

        certificateIssueService.createCertificateIssue(residentSerialNumber, CertificateTypeCode.출생신고서);

        BirthDeathReportResidentDto birthReportResidentDto = birthDeathReportResidentService.findBirthReportResidentByResidentSerialNumber(residentSerialNumber, BirthDeathTypeCode.출생);
        model.addAttribute("birthReportResident", birthReportResidentDto);;

        ResidentDto residentDto = residentService.getResidentByResidentSerialNumber(residentSerialNumber);
        model.addAttribute("resident", residentDto);

        ResidentDto fatherResidentDto = familyRelationshipService.findFamilyRelationshipResident(residentSerialNumber, "부");
        model.addAttribute("father", fatherResidentDto);

        ResidentDto motherResidentDto = familyRelationshipService.findFamilyRelationshipResident(residentSerialNumber, "모");
        model.addAttribute("mother", motherResidentDto);

        return "birthreport";
    }

    //사망신고서 조회
    @GetMapping("/residents/{residentSerialNumber}/death-report")
    public String getDeathReportCertificate(@PathVariable("residentSerialNumber") long residentSerialNumber, Model model){

        certificateIssueService.createCertificateIssue(residentSerialNumber, CertificateTypeCode.사망신고서);

        BirthDeathReportResidentDto deathReportResidentDto = birthDeathReportResidentService.findBirthReportResidentByResidentSerialNumber(residentSerialNumber, BirthDeathTypeCode.사망);
        model.addAttribute("deathReportResident", deathReportResidentDto);;

        ResidentDto residentDto = residentService.getResidentByResidentSerialNumber(residentSerialNumber);
        model.addAttribute("resident", residentDto);

        return "deathreport";
    }

    //주민별 증명서 발급 목록 조회
    @GetMapping("/residents/{residentSerialNumber}/certificate-issuance-list")
    public String getCertificateIssues(@PageableDefault(size = 10) Pageable pageable, @PathVariable("residentSerialNumber") long residentSerialNumber, Model model){

        Page<CertificateIssueDto> certificateIssueDtos = certificateIssueService.getCertificateIssuesByResidentSerialNumber(pageable, residentSerialNumber);
        model.addAttribute("certificateIssues", certificateIssueDtos);

        return "certificateissues";
    }



}
