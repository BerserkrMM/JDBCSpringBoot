package com.example.jdbcspringbootapp.model.dto.request.cardr;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

//ОБОВ₴ЯЗКОВО анотації для ДТО:
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardReqDto {
    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long currency_id;

    @NotNull
    private String name;

    @NotNull
    private String bank_name;
}
