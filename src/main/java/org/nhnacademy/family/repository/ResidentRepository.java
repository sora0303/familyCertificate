package org.nhnacademy.family.repository;

import org.nhnacademy.family.domain.ResidentDto;
import org.nhnacademy.family.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

    Page<ResidentDto> getAllBy(Pageable pageable);

    ResidentDto getResidentByResidentSerialNumber(long residentSerialNumber);

}
