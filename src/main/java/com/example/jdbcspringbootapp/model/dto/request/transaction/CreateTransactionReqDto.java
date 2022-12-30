package com.example.jdbcspringbootapp.model.dto.request.transaction;

import com.example.jdbcspringbootapp.model.enums.Transaction_Directions;
import lombok.*;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

//ОБОВ₴ЯЗКОВО анотації для ДТО:
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionReqDto {
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
