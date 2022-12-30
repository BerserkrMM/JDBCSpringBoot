package com.example.jdbcspringbootapp.model.dto.response.currency;

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
public class CreateCurrencyRespDto extends BaseFieldsResponseDTO {
    private String name;
    private String code;
    private BigDecimal exchangeratetousd;
}
