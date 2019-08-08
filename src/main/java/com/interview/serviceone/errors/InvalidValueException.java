package com.interview.serviceone.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InvalidValueException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
}
