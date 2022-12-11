package com.example.jdbcspringbootapp.model.dto.response.transactionResponses;

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
public class GetFirstTransactionRespDto extends BaseFieldsResponseDTO {
    private TRANSACTION_DIRECTIONS direction;
    private BigDecimal amount;
    private Long cardId;
    private Long currencyId;
    private Long transactionCategoryID;
    private String infoExtra;
}
