package com.example.jdbcspringbootapp.model.dto.request.transactionCategoriesRequests;

import com.example.jdbcspringbootapp.model.enums.TRANSACTION_DIRECTIONS;
import lombok.*;
import org.jetbrains.annotations.NotNull;

//ОБОВ₴ЯЗКОВО анотації для ДТО:
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionCategoryReqDto {
    @NotNull
    private TRANSACTION_DIRECTIONS type;
    @NotNull
    private String name;
}
