package com.example.jdbcspringbootapp.model.enums;

public enum STATUS {
    OK(1),
    FAILED(2),
    FORBIDDEN(3);

    public Integer status;

    private STATUS(Integer status) {
        this.status = status;
    }
}
