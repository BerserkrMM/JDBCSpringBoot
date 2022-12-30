package com.example.jdbcspringbootapp.model.dto.request.currency;

import lombok.*;

import javax.validation.constraints.NotNull;
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
