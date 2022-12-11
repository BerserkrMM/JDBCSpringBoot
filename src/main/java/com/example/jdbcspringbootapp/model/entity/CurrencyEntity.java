package com.example.jdbcspringbootapp.model.entity;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Getter
public class CurrencyEntity {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String code;

    @NotNull
    private BigDecimal exchangeRateToUSD;
}
