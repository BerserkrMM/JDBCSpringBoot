package com.example.jdbcspringbootapp.model.dto.response.transaction;

import com.example.jdbcspringbootapp.model.dto.response.BaseFieldsResponseDTO;
import com.example.jdbcspringbootapp.model.enums.Transaction_Directions;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRespDto extends BaseFieldsResponseDTO {
    private Transaction_Directions direction;
    private BigDecimal amount;
    private Long card_id;
    private Long currency_id;
    private Long transaction_category_id;
    private String info_extra;

}
