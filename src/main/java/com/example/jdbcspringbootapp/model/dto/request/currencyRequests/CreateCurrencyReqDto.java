package com.example.jdbcspringbootapp.model.dto.request.currencyRequests;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

//ОБОВ₴ЯЗКОВО анотації для ДТО:
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCurrencyReqDto {
    @NotNull
    private String name;

    @NotNull
    private String code;

    @NotNull
    private BigDecimal exchangeRateToUSD;
}
