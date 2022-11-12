package com.example.jdbcspringbootapp.model.entity;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class CurrencyEntity {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String code;

    @NotNull
    private BigDecimal exchangeRateToUSD;
}
