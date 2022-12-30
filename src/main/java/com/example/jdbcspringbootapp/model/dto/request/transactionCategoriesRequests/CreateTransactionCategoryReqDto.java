package com.example.jdbcspringbootapp.model.dto.request.transactionCategoriesRequests;

import com.example.jdbcspringbootapp.model.enums.Transaction_Directions;
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
    private Transaction_Directions type;
    @NotNull
    private String name;
}
