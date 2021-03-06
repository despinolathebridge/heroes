package com.heroes.app.exception;

public enum HeroeExceptionEnum {
    HEROE_NOT_FOUND("HRO-001", "Heroe not found in the database."),
    INVALID_JWT("HRO-002", "Invalid JWT exception.");

    public final String errorCode;
    public final String errorDescription;

    HeroeExceptionEnum(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
