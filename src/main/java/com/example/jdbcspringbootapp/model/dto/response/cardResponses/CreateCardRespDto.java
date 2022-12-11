package com.example.jdbcspringbootapp.model.dto.response.cardResponses;

import com.example.jdbcspringbootapp.model.dto.response.BaseFieldsResponseDTO;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardRespDto extends BaseFieldsResponseDTO {

    private BigDecimal amount; //set ZERO by default!!!

    private Long currencyId;

    private String cardName;

    private String bankName;

}
