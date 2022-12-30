package com.example.jdbcspringbootapp.model.dto.request.cardr;

import lombok.*;
import java.math.BigDecimal;

//ОБОВ₴ЯЗКОВО анотації для ДТО:
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardReqDto {
    private BigDecimal amount;
    private Long currencyId;
    private String cardName;
    private String bankName;
}
