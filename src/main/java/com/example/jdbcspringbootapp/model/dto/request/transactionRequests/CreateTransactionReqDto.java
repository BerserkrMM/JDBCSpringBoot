package com.example.jdbcspringbootapp.model.dto.request.transactionRequests;

import com.example.jdbcspringbootapp.model.enums.TRANSACTION_DIRECTIONS;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

//ОБОВ₴ЯЗКОВО анотації для ДТО:
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionReqDto {
    @NotNull
    private TRANSACTION_DIRECTIONS direction;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long cardId;

    @NotNull
    private Long currencyId;

    private Long transactionCategoryID;

    private String infoExtra;
}
