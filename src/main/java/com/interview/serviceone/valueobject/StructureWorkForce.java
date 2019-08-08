package com.interview.serviceone.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.validation.constraints.*;


@JsonPropertyOrder({ "rooms", "senior", "junior"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StructureWorkForce {

    @JsonProperty(value= "rooms", required = true)
    @NotNull(message="Rooms values are missing")
    private Integer[] rooms;

    @Min(value=1l, message="One Senior is mandatory")
    @Max(value=100l, message="Senior Capacity should not exceed 100")
    @JsonProperty(value="senior", required = true)
    @NotNull(message = "Senior capacity is missing")
    private Integer seniorCapacity;

    @Min(value=0l, message ="Junior Capacity cannot be negative")
    @Max(value=99l, message="Junior Capacity should be less than 100")
    @JsonProperty(value= "junior", required = true)
    @NotNull(message = "Junior capacity is missing")
    private Integer juniorCapacity;
}
