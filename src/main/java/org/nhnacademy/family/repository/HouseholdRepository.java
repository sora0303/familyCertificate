package org.nhnacademy.family.repository;

import org.nhnacademy.family.domain.HouseholdDto;
import org.nhnacademy.family.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Long> {

    HouseholdDto getHouseholdByHouseholdSerialNumber(long householdSerialNumber);

    HouseholdDto getHouseholdByResident_ResidentSerialNumber(long residentSerialNumber);
}
