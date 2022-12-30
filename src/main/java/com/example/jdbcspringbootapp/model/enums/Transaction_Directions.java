package com.example.jdbcspringbootapp.model.enums;

public enum Transaction_Directions {

    INCOME(1),
    EXPENSE(-1);

    public Integer status;

    private Transaction_Directions(Integer status) {
        this.status = status;
    }

}
