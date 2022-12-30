package com.example.jdbcspringbootapp.model.dto.request.transaction;

import com.example.jdbcspringbootapp.model.enums.Transaction_Directions;
import lombok.*;
import java.math.BigDecimal;

//ОБОВ₴ЯЗКОВО анотації для ДТО:
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTransactionReqDto {
    private Transaction_Directions direction;
    private BigDecimal amount;
    private Long cardId;
    private Long currencyId;
    private Long transactionCategoryID;
    private String infoExtra;
}
