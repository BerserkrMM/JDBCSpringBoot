package com.example.jdbcspringbootapp.model.entity;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class CardEntity {

    private Long id;

    @NotNull
    private BigDecimal amount = BigDecimal.valueOf(0); //set ZERO by default!!!

    @NotNull
    private Long currencyId;

    private String simpleName = "Card " + this.hashCode();

    @NotNull
    private String bankName;

}
