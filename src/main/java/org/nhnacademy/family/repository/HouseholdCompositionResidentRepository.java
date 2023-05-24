package org.nhnacademy.family.repository;

import java.util.List;
import org.nhnacademy.family.domain.HouseholdCompositionResidentDto;
import org.nhnacademy.family.domain.HouseholdSerialNumberDto;
import org.nhnacademy.family.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdCompositionResidentRepository
    extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.Pk> {

    HouseholdSerialNumberDto findHouseholdCompositionResidentByResident_ResidentSerialNumber(long residentSerialNumber);

    List<HouseholdCompositionResidentDto> findHouseholdCompositionResidentByHousehold_HouseholdSerialNumber(long householdSerialNumber);
}
