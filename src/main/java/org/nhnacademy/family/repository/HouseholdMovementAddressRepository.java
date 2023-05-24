package org.nhnacademy.family.repository;

import java.util.List;
import org.nhnacademy.family.domain.HouseholdMovementAddressDto;
import org.nhnacademy.family.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {

    @Query("SELECT a.houseMovementAddress AS houseMovementAddress, a.pk.houseMovementReportDate AS houseMovementReportDate, " +
        "a.lastAddressYn AS lastAddressYn " +
        "FROM HouseholdMovementAddress a " +
        "INNER JOIN a.household h " +
        "WHERE a.pk.householdSerialNumber = :householdSerialNumber " +
        "ORDER BY a.pk.houseMovementReportDate DESC")
    List<HouseholdMovementAddressDto> getHouseholdMovementAddresses(@Param("householdSerialNumber") long householdSerialNumber);

}
