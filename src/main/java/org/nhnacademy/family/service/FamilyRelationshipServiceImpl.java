package org.nhnacademy.family.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.nhnacademy.family.domain.FamilyRelationshipDto;
import org.nhnacademy.family.domain.FamilyRelationshipModifyRequest;
import org.nhnacademy.family.domain.FamilyRelationshipRegisterRequest;
import org.nhnacademy.family.domain.ResidentDto;
import org.nhnacademy.family.entity.FamilyRelationship;
import org.nhnacademy.family.entity.Resident;
import org.nhnacademy.family.exception.FamilyRelationshipNotFoundException;
import org.nhnacademy.family.exception.ResidentNotFoundException;
import org.nhnacademy.family.repository.FamilyRelationshipRepository;
import org.nhnacademy.family.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("familyRelationshipService")
@RequiredArgsConstructor
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {

    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    private FamilyRelationship getFamilyRelationship(long residentSerialNumber, long familySerialNumber) {

        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();
        pk.setBaseResidentSerialNumber(residentSerialNumber);
        pk.setFamilyResidentSerialNumber(familySerialNumber);
        return familyRelationshipRepository.findById(pk)
            .orElseThrow(() -> new FamilyRelationshipNotFoundException(residentSerialNumber, familySerialNumber));
    }

    @Override
    @Transactional
    public void createFamilyRelationship(long residentSerialNumber,
                                         FamilyRelationshipRegisterRequest familyRelationshipRegisterRequest) {

        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();
        pk.setBaseResidentSerialNumber(residentSerialNumber);
        pk.setFamilyResidentSerialNumber(familyRelationshipRegisterRequest.getFamilySerialNumber());

        Resident residentFamily = residentRepository.findById(familyRelationshipRegisterRequest.getFamilySerialNumber())
            .orElseThrow(() -> new ResidentNotFoundException(residentSerialNumber));
        Resident residentBase = residentRepository.findById(residentSerialNumber)
            .orElseThrow(() -> new ResidentNotFoundException(residentSerialNumber));

        FamilyRelationship familyRelationship = new FamilyRelationship();
        familyRelationship.setPk(pk);
        familyRelationship.setResidentFamily(residentFamily);
        familyRelationship.setResidentBase(residentBase);
        familyRelationship.setFamilyRelationshipCode(familyRelationshipRegisterRequest.getRelationship());

        familyRelationshipRepository.save(familyRelationship);
    }

    @Override
    @Transactional
    public void modifyFamilyRelationship(long residentSerialNumber, long familySerialNumber,
                                         FamilyRelationshipModifyRequest familyRelationshipModifyRequest) {

        FamilyRelationship familyRelationship = getFamilyRelationship(residentSerialNumber, familySerialNumber);
        familyRelationship.setFamilyRelationshipCode(familyRelationshipModifyRequest.getRelationship());
        familyRelationshipRepository.save(familyRelationship);
    }

    @Override
    @Transactional
    public void deleteFamilyRelationship(long residentSerialNumber, long familySerialNumber) {

        FamilyRelationship familyRelationship = getFamilyRelationship(residentSerialNumber, familySerialNumber);
        familyRelationshipRepository.delete(familyRelationship);
    }

    @Override
    public ResidentDto findFamilyRelationshipResident(Long residentSerialNumber, String familyRelationshipCode) {
        return familyRelationshipRepository.findFamilyRelationshipResident(residentSerialNumber, familyRelationshipCode);
    }

    @Override
    public List<FamilyRelationshipDto> findAllByResidentBase_ResidentSerialNumber(long residentSerialNumber) {
        return familyRelationshipRepository.findAllByResidentBase_ResidentSerialNumber(residentSerialNumber);
    }
}
