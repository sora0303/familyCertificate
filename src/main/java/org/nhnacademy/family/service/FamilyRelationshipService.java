package org.nhnacademy.family.service;

import java.util.List;
import org.nhnacademy.family.domain.FamilyRelationshipDto;
import org.nhnacademy.family.domain.FamilyRelationshipModifyRequest;
import org.nhnacademy.family.domain.FamilyRelationshipRegisterRequest;
import org.nhnacademy.family.domain.ResidentDto;

public interface FamilyRelationshipService {

    void createFamilyRelationship(long residentSerialNumber, FamilyRelationshipRegisterRequest familyRelationshipRegisterRequest);

    void modifyFamilyRelationship(long residentSerialNumber, long familySerialNumber, FamilyRelationshipModifyRequest familyRelationshipModifyRequest);

    void deleteFamilyRelationship(long residentSerialNumber, long familySerialNumber);

    ResidentDto findFamilyRelationshipResident(Long residentSerialNumber, String familyRelationshipCode);

    List<FamilyRelationshipDto> findAllByResidentBase_ResidentSerialNumber(long residentSerialNumber);


}
