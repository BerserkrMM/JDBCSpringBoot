package com.example.jdbcspringbootapp.model.dto.response.tranactionCategoriesResponses;

import com.example.jdbcspringbootapp.model.dto.response.BaseFieldsResponseDTO;
import com.example.jdbcspringbootapp.model.enums.TRANSACTION_DIRECTIONS;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionCategoryRespDto extends BaseFieldsResponseDTO {
    private TRANSACTION_DIRECTIONS type;
    private String name;
}
