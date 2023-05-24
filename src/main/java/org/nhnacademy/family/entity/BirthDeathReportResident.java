package org.nhnacademy.family.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nhnacademy.family.enumclass.BirthDeathTypeCode;
import org.nhnacademy.family.enumclass.BirthReportQualificationsCode;
import org.nhnacademy.family.enumclass.DeathReportQualificationsCode;

@Getter
@Setter
@Entity
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {

    @EmbeddedId
    private Pk pk;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @MapsId("reportResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "report_resident_serial_number")
    private Resident residentReport;

    @Column(name = "birth_death_report_date")
    private LocalDate birthDeathReportDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "birth_report_qualifications_code")
    private BirthReportQualificationsCode birthReportQualificationsCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "death_report_qualifications_code")
    private DeathReportQualificationsCode deathReportQualificationsCode;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {

        @Enumerated(EnumType.STRING)
        @Column(name = "birth_death_type_code")
        private BirthDeathTypeCode birthDeathTypeCode;

        @Column(name = "resident_serial_number")
        private long residentSerialNumber;

        @Column(name = "report_resident_serial_number")
        private long reportResidentSerialNumber;
    }







}
