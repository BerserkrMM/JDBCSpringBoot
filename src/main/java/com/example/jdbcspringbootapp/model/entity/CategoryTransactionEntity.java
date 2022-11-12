package com.example.jdbcspringbootapp.model.entity;

import com.example.jdbcspringbootapp.model.enums.TRANSACTION_DIRECTIONS;
import org.jetbrains.annotations.NotNull;

public class CategoryTransactionEntity {

    private Long id;

    @NotNull
    private TRANSACTION_DIRECTIONS type;


    @NotNull
    private String name;
}