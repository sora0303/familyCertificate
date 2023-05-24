package org.nhnacademy.family.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "resident")
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_serial_number")
    private Long residentSerialNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "resident_registration_number")
    private String residentRegistrationNumber;

    @Column(name = "gender_code")
    private String genderCode;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code")
    private String birthPlaceCode;

    @Column(name = "registration_base_address")
    private String registrationBaseAddress;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code")
    private String deathPlaceCode;

    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    @OneToMany(mappedBy = "resident", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<BirthDeathReportResident> birthDeathReportResidents = new ArrayList<>();

    @OneToMany(mappedBy = "residentReport", cascade = CascadeType.REMOVE)
    private List<BirthDeathReportResident> birthDeathReportResidentsReports = new ArrayList<>();

    @OneToMany(mappedBy = "residentBase", cascade = CascadeType.REMOVE)
    private List<FamilyRelationship> familyRelationshipsBase = new ArrayList<>();

    @OneToMany(mappedBy = "residentFamily", cascade = CascadeType.REMOVE)
    private List<FamilyRelationship> familyRelationshipsFamily = new ArrayList<>();


    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<CertificateIssue> certificateIssues = new ArrayList<>();

    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<Household> households = new ArrayList<>();

    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<HouseholdCompositionResident> householdCompositionResidents = new ArrayList<>();

}
