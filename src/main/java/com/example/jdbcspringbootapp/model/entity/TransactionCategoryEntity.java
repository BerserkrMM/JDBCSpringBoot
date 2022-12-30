package com.example.jdbcspringbootapp.model.entity;

import com.example.jdbcspringbootapp.model.enums.Transaction_Directions;
import org.jetbrains.annotations.NotNull;

public class TransactionCategoryEntity {

    private Long id;

    @NotNull
    private Transaction_Directions type;

    @NotNull
    private String name;
}