package com.example.jdbcspringbootapp.model.dto.request.currencyRequests;

import lombok.*;

import java.math.BigDecimal;

//ОБОВ₴ЯЗКОВО анотації для ДТО:
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCurrencyReqDto {
    private String name;
    private String code;
    private BigDecimal exchangeRateToUSD;
}
