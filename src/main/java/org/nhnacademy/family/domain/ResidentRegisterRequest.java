package org.nhnacademy.family.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResidentRegisterRequest {

    @NotNull
    private String name;

    @NotNull
    private String residentRegistrationNumber;

    @NotNull
    private String genderCode;

    @NotNull
    private LocalDateTime birthDate;

    @NotNull
    private String birthPlaceCode;

    @NotNull
    private String registrationBaseAddress;

    private LocalDateTime deathDate;

    private String deathPlaceCode;

    private String deathPlaceAddress;

}
