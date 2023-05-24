package org.nhnacademy.family.domain;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.nhnacademy.family.entity.HouseholdCompositionResident;
import org.nhnacademy.family.entity.HouseholdMovementAddress;
import org.nhnacademy.family.entity.Resident;

@Getter
@Setter
public class HouseholdRegisterRequest {

    @NotNull
    private long residentSerialNumber;

    @NotNull
    private LocalDate householdCompositionDate;

    @NotNull
    private String householdCompositionReasonCode;

    @NotNull
    private String currentHouseMovementAddress;
}
