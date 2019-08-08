package com.interview.serviceone.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Arrays;

/**
 * @author Mohamed.Shabeer
 * This Value Object holds optimized work force result
 */
@JsonPropertyOrder({ "rooms", "senior", "junior"})
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OptimizedWorkForce {

    @JsonProperty("rooms")
    private int rooms;

    @JsonProperty("senior")
    private int requiredSeniors;

    @JsonProperty("junior")
    private int requiredJuniors;

    public OptimizedWorkForce(int rooms, int requiredSeniors, int requiredJuniors) {
        this.rooms = rooms;
        this.requiredSeniors = requiredSeniors;
        this.requiredJuniors = requiredJuniors;
    }
}
