package com.example.jdbcspringbootapp.model.entity;

import com.example.jdbcspringbootapp.model.enums.TRANSACTION_DIRECTIONS;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class TransactionEntity {

    private Long id;

    @NotNull
    private TRANSACTION_DIRECTIONS direction;

    private BigDecimal amount;

    @NotNull
    private Long cardId;

    @NotNull
    private Long currencyId;

    private CategoryTransactionEntity category;

    private String infoExtra;

}
