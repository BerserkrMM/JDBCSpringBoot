package com.example.jdbcspringbootapp.model.dto.request.transactioncategories;

import com.example.jdbcspringbootapp.model.enums.Transaction_Directions;
import lombok.*;

//ОБОВ₴ЯЗКОВО анотації для ДТО:
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTransactionCategoryReqDto {
    private Transaction_Directions type;
    private String name;
}
