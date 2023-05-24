package org.nhnacademy.family.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class FamilyRelationshipRegisterRequest {

    @NotNull
    private long familySerialNumber;

    @NotNull
    @NotBlank
    private String relationship;
}
