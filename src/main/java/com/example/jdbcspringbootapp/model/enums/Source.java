package com.example.jdbcspringbootapp.model.enums;

public enum Source {
    CARD(1),
    CURRENCY(2),
    TRANSACTION_CATEGORY(3),
    TRANSACTION(4);

    public Integer status;

    private Source(Integer status) {
        this.status = status;
    }
}
