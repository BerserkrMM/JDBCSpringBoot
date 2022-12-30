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
    private BigDecimal amount = null;
    private Long currencyId = null;
    private String cardName = null;
    private String bankName = null;
}
