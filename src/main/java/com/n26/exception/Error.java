package com.n26.exception;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Error {

    private final int status;

    private final String message;

    private List<FieldError> fieldErrors = new ArrayList<>();

    Error(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public void addFieldError(String objectName, String path, String message) {
        FieldError error = new FieldError(objectName, path, message);
        fieldErrors.add(error);
    }

}