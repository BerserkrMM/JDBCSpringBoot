package com.example.jdbcspringbootapp.model.dto.request.transactionRequests;

import com.example.jdbcspringbootapp.model.enums.TRANSACTION_DIRECTIONS;
import lombok.*;
import java.math.BigDecimal;

//ОБОВ₴ЯЗКОВО анотації для ДТО:
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTransactionReqDto {
    private TRANSACTION_DIRECTIONS direction;
    private BigDecimal amount;
    private Long cardId;
    private Long currencyId;
    private Long transactionCategoryID;
    private String infoExtra;
}
