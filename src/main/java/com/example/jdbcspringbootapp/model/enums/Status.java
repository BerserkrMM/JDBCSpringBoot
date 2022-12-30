package com.example.jdbcspringbootapp.model.enums;

public enum Status {
    OK(1),
    FAILED(2),
    FORBIDDEN(3);

    public Integer status;

    private Status(Integer status) {
        this.status = status;
    }
}
