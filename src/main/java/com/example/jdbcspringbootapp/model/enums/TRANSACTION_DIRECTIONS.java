package com.example.jdbcspringbootapp.model.enums;

public enum TRANSACTION_DIRECTIONS {

    INCOME(1),
    EXPENSE(-1);

    public Integer status;

    private TRANSACTION_DIRECTIONS(Integer status) {
        this.status = status;
    }

}
