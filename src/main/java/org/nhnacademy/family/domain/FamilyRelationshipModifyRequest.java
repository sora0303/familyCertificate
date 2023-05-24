package org.nhnacademy.family.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class FamilyRelationshipModifyRequest {

    @NotNull
    @NotBlank
    private String relationship;

}
