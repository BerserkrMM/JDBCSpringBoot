package com.example.jdbcspringbootapp.model.dto.request.transactionCategoriesRequests;

import com.example.jdbcspringbootapp.model.enums.TRANSACTION_DIRECTIONS;
import lombok.*;

//ОБОВ₴ЯЗКОВО анотації для ДТО:
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTransactionCategoryReqDto {
    private TRANSACTION_DIRECTIONS type;
    private String name;
}
