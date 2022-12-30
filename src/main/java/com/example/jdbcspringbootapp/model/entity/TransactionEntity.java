package com.example.jdbcspringbootapp.model.entity;

import com.example.jdbcspringbootapp.model.enums.Transaction_Directions;
import org.jetbrains.annotations.NotNull;
import java.math.BigDecimal;

public class TransactionEntity {

    private Long id;

    @NotNull
    private Transaction_Directions direction;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long cardId;

    @NotNull
    private Long currencyId;

    private Long transactionCategoryID;

    private String infoExtra;
}
