package com.elderbr.clients.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, int status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public List<FieldMessage> add(FieldMessage fielddMessage) {
        errors.add(fielddMessage);
        return errors;
    }

    public List<FieldMessage> add(String fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
        return errors;
    }
}
