package com.example.jdbcspringbootapp.model.enums;

public enum IsDeleted {

    N(0),
    Y(1);

    public Integer is_deleted;

    private IsDeleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }
}
