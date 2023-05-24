package org.nhnacademy.family.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhnacademy.family.domain.HouseholdMovementAddressModifyRequest;
import org.nhnacademy.family.domain.HouseholdMovementAddressRegisterRequest;
import org.nhnacademy.family.domain.HouseholdRegisterRequest;
import org.nhnacademy.family.domain.ResidentRegisterRequest;
import org.nhnacademy.family.entity.HouseholdMovementAddress;
import org.nhnacademy.family.exception.ValidationFailedException;
import org.nhnacademy.family.service.BirthDeathReportResidentService;
import org.nhnacademy.family.service.HouseholdMovementAddressService;
import org.nhnacademy.family.service.HouseholdService;
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

@RestController
@RequestMapping("/household")
@RequiredArgsConstructor
@Slf4j
public class HouseholdRestController {

    private final HouseholdService householdService;

    private final HouseholdMovementAddressService householdMovementAddressService;

    //세대 등록
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = {"/", ""})
    public HttpStatus createHousehold(@RequestBody @Valid HouseholdRegisterRequest householdRegisterRequest,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        householdService.createHousehold(householdRegisterRequest);
        return HttpStatus.CREATED;
    }

    //세대 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{householdSerialNumber}")
    public HttpStatus deleteHousehold(@PathVariable("householdSerialNumber") long householdSerialNumber) {

        householdService.deleteHousehold(householdSerialNumber);
        return HttpStatus.OK;
    }

    //세대 전입 주소 등록
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{householdSerialNumber}/movement")
    public HttpStatus createHouseholdMovementAddress(@PathVariable("householdSerialNumber") long householdSerialNumber, @RequestBody @Valid HouseholdMovementAddressRegisterRequest householdMovementAddressRegisterRequest,
                                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        householdMovementAddressService.createHouseholdMovementAddress(householdSerialNumber, householdMovementAddressRegisterRequest);
        return HttpStatus.CREATED;
    }

    //세대 전입 주소 등록
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{householdSerialNumber}/movement/{reportDate}")
    public HttpStatus createHouseholdMovementAddress(@PathVariable("householdSerialNumber") long householdSerialNumber, @PathVariable("reportDate") String reportDate, @RequestBody @Valid
                                                     HouseholdMovementAddressModifyRequest householdMovementAddressModifyRequest,
                                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        householdMovementAddressService.modifyHouseholdMovementAddress(householdSerialNumber, reportDate, householdMovementAddressModifyRequest);
        return HttpStatus.OK;
    }

    //세대 전입 주소 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{householdSerialNumber}/movement/{reportDate}")
    public HttpStatus deleteHouseholdMovementAddress(@PathVariable("householdSerialNumber") long householdSerialNumber, @PathVariable("reportDate") String reportDate) {

        householdMovementAddressService.deleteHouseholdMovementAddress(householdSerialNumber, reportDate);
        return HttpStatus.OK;
    }


}
