package com.example.jdbcspringbootapp.model.dto.response.card;

import com.example.jdbcspringbootapp.model.dto.response.BaseFieldsResponseDTO;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCardRespDto extends BaseFieldsResponseDTO {

    private BigDecimal amount;

    private Long currency_id;

    private String name;

    private String bank_name;

}
