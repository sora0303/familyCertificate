package org.nhnacademy.family.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nhnacademy.family.config.RootConfig;
import org.nhnacademy.family.config.WebConfig;
import org.nhnacademy.family.entity.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
    @ContextConfiguration(classes = RootConfig.class),
    @ContextConfiguration(classes = WebConfig.class)
})
class ResidentRepositoryTest {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    ResidentRepository residentRepository;

    @Test
    void test(){
        Resident residentExpected = new Resident();
        residentExpected.setName("남길동");
        residentExpected.setResidentRegistrationNumber("130914-1234561");
        residentExpected.setGenderCode("남");
        residentExpected.setBirthDate(LocalDateTime.parse("19130914072200", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        residentExpected.setBirthPlaceCode("자택");
        residentExpected.setRegistrationBaseAddress("경기도 성남시 분당구 대왕판교로645번길");
        residentExpected.setDeathDate(LocalDateTime.parse("20210429090300", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        residentExpected.setDeathPlaceCode("주택");
        residentExpected.setDeathPlaceAddress("강원도 고성군 금강산로 290번길");

        residentRepository.save(residentExpected);

        Optional<Resident> residentActual = residentRepository.findById(residentExpected.getResidentSerialNumber());

        Assumptions.assumeThat(residentActual.isPresent()).isTrue();

        Assertions.assertThat(residentExpected.getName()).isEqualTo(residentActual.get().getName());}

    @Test
    void test2(){
        Resident residentExpected = new Resident();
        residentExpected.setName("남길동");
        residentExpected.setResidentRegistrationNumber("130914-1234561");
        residentExpected.setGenderCode("남");
        residentExpected.setBirthDate(LocalDateTime.parse("19130914072200", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        residentExpected.setBirthPlaceCode("자택");
        residentExpected.setRegistrationBaseAddress("경기도 성남시 분당구 대왕판교로645번길");

        residentRepository.save(residentExpected);

        Optional<Resident> residentActual = residentRepository.findById(1L);
        Assumptions.assumeThat(residentActual.isPresent()).isTrue();
        Assertions.assertThat(residentExpected.getName()).isEqualTo(residentActual.get().getName());
    }
}