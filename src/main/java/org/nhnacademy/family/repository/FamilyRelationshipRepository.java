package org.nhnacademy.family.repository;

import java.util.List;
import org.nhnacademy.family.domain.FamilyRelationshipDto;
import org.nhnacademy.family.domain.ResidentDto;
import org.nhnacademy.family.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {

    @Query("SELECT r2.name AS name, r2.residentRegistrationNumber AS residentRegistrationNumber " +
        "FROM FamilyRelationship f " +
        "INNER JOIN f.residentBase r1 " +
        "INNER JOIN f.residentFamily r2 " +
        "WHERE f.familyRelationshipCode =:familyRelationshipCode AND r1.residentSerialNumber = :residentSerialNumber")
    ResidentDto findFamilyRelationshipResident(@Param("residentSerialNumber") Long residentSerialNumber, @Param("familyRelationshipCode")String familyRelationshipCode);

    @Query("SELECT r2.name AS name, r2.residentRegistrationNumber AS residentRegistrationNumber, " +
        "f.familyRelationshipCode AS familyRelationshipCode, r2.genderCode AS genderCode, r2.birthDate AS birthDate " +
        "FROM FamilyRelationship f " +
        "INNER JOIN f.residentBase r1 " +
        "INNER JOIN f.residentFamily r2 " +
        "WHERE r1.residentSerialNumber = :residentSerialNumber")
    List<FamilyRelationshipDto> findAllByResidentBase_ResidentSerialNumber(@Param("residentSerialNumber")long residentSerialNumber);

}
