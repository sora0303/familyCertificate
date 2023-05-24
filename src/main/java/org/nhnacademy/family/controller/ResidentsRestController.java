package org.nhnacademy.family.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhnacademy.family.domain.BirthReportModifyRequest;
import org.nhnacademy.family.domain.BirthReportRegisterRequest;
import org.nhnacademy.family.domain.DeathReportModifyRequest;
import org.nhnacademy.family.domain.DeathReportRegisterRequest;
import org.nhnacademy.family.domain.FamilyRelationshipModifyRequest;
import org.nhnacademy.family.domain.FamilyRelationshipRegisterRequest;
import org.nhnacademy.family.domain.ResidentRegisterRequest;
import org.nhnacademy.family.exception.ValidationFailedException;
import org.nhnacademy.family.service.BirthDeathReportResidentService;
import org.nhnacademy.family.service.FamilyRelationshipService;
import org.nhnacademy.family.service.ResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
@Slf4j
public class ResidentsRestController {

    private final ResidentService residentService;

    private final FamilyRelationshipService familyRelationshipService;

    private final BirthDeathReportResidentService birthDeathReportResidentService;

    //주민등록
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = {"/", ""})
    public HttpStatus createResident(@RequestBody @Valid ResidentRegisterRequest residentRegisterRequest,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        residentService.createResident(residentRegisterRequest);
        return HttpStatus.CREATED;
    }

    //주민수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{serialNumber}")
    public HttpStatus modifyResident(@PathVariable("serialNumber") long residentSerialNumber,
                                     @RequestBody @Valid ResidentRegisterRequest residentRegisterRequest,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        residentService.modifyResident(residentSerialNumber, residentRegisterRequest);
        return HttpStatus.OK;
    }

    //주민삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{residentSerialNumber}")
    public void deleteResident(@PathVariable("residentSerialNumber") long residentSerialNumber, HttpServletResponse response)
        throws IOException {
        residentService.deleteResident(residentSerialNumber);
        response.sendRedirect("/residents");
    }

    //가족관계 등록
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{serialNumber}/relationship")
    public HttpStatus createFamilyRelationship(@PathVariable("serialNumber") long residentSerialNumber, @RequestBody
    @Valid FamilyRelationshipRegisterRequest familyRelationshipRegisterRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        familyRelationshipService.createFamilyRelationship(residentSerialNumber, familyRelationshipRegisterRequest);
        return HttpStatus.CREATED;
    }

    //가족관계 수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "{serialNumber}/relationship/{familySerialNumber}")
    public HttpStatus modifyFamilyRelationship(@PathVariable("serialNumber") long residentSerialNumber,
                                               @PathVariable("familySerialNumber") long familySerialNumber, @RequestBody
                                               @Valid FamilyRelationshipModifyRequest familyRelationshipModifyRequest,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        familyRelationshipService.modifyFamilyRelationship(residentSerialNumber, familySerialNumber,
            familyRelationshipModifyRequest);

        return HttpStatus.OK;
    }

    //가족관계 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "{serialNumber}/relationship/{familySerialNumber}")
    public void deleteFamilyRelationship(@PathVariable("serialNumber") long residentSerialNumber,
                                         @PathVariable("familySerialNumber") long familySerialNumber) {
        familyRelationshipService.deleteFamilyRelationship(residentSerialNumber, familySerialNumber);
    }


    //출생신고서 등록
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{serialNumber}/birth")
    public HttpStatus createBirthReportResident(@PathVariable("serialNumber") long residentSerialNumber,
                                                @RequestBody @Valid
                                                BirthReportRegisterRequest birthReportRegisterRequest,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        birthDeathReportResidentService.createBirthReportResident(residentSerialNumber, birthReportRegisterRequest);

        return HttpStatus.CREATED;
    }

    //출생신고서 수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "{serialNumber}/birth/{targetSerialNumber}")
    public HttpStatus modifyBirthReportResident(@PathVariable("serialNumber") long residentSerialNumber,
                                                @PathVariable("targetSerialNumber") long targetSerialNumber,
                                                @RequestBody @Valid
                                                BirthReportModifyRequest birthReportModifyRequest,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        birthDeathReportResidentService.modifyBirthReportResident(residentSerialNumber, targetSerialNumber,
            birthReportModifyRequest);

        return HttpStatus.OK;
    }

    //출생신고서 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "{serialNumber}/birth/{targetSerialNumber}")
    public void deleteBirthReportResident(@PathVariable("serialNumber") long residentSerialNumber,
                                          @PathVariable("targetSerialNumber") long targetSerialNumber) {
        birthDeathReportResidentService.deleteBirthReportResident(residentSerialNumber, targetSerialNumber);
    }

    //사망신고서 등록
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{serialNumber}/death")
    public HttpStatus createDeathReportResident(@PathVariable("serialNumber") long residentSerialNumber,
                                                @RequestBody @Valid
                                                DeathReportRegisterRequest deathReportRegisterRequest,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        birthDeathReportResidentService.createDeathReportResident(residentSerialNumber, deathReportRegisterRequest);

        return HttpStatus.CREATED;
    }

    //사망신고서 수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "{serialNumber}/death/{targetSerialNumber}")
    public HttpStatus modifyDeathReportResident(@PathVariable("serialNumber") long residentSerialNumber,
                                                @PathVariable("targetSerialNumber") long targetSerialNumber,
                                                @RequestBody @Valid
                                                DeathReportModifyRequest deathReportModifyRequest,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        birthDeathReportResidentService.modifyDeathReportResident(residentSerialNumber, targetSerialNumber,
            deathReportModifyRequest);

        return HttpStatus.OK;
    }

    //사망신고서 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "{serialNumber}/death/{targetSerialNumber}")
    public void deleteDeathReportResident(@PathVariable("serialNumber") long residentSerialNumber,
                                          @PathVariable("targetSerialNumber") long targetSerialNumber) {
        birthDeathReportResidentService.deleteDeathReportResident(residentSerialNumber, targetSerialNumber);
    }

}
