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
import org.nhnacademy.family.entity.BirthDeathReportResident;
import org.nhnacademy.family.entity.Resident;
import org.springframework.beans.factory.annotation.Autowired;
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
class BirthDeathReportResidentRepositoryTest {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    BirthDeathReportResidentRepository repository;

    @Test
    void test() {

//        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
//        birthDeathReportResident.set
//
//        residentRepository.save(residentExpected);
//
//        Optional<Resident> residentActual = residentRepository.findById(1L);
//
//        Assumptions.assumeThat(residentActual.isPresent()).isTrue();
//
//        Assertions.assertThat(residentExpected.getName()).isEqualTo(residentActual.get().getName());
    }
}