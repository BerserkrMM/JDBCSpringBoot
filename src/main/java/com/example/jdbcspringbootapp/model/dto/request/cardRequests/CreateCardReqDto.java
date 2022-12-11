package com.example.jdbcspringbootapp.model.dto.request.cardRequests;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import java.math.BigDecimal;

//ОБОВ₴ЯЗКОВО анотації для ДТО:
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardReqDto {
    @NotNull
    private BigDecimal amount = BigDecimal.valueOf(0); //set ZERO by default!!!

    @NotNull
    private Long currencyId;

    @NotNull
    private String cardName = "Card " + this.hashCode();

    @NotNull
    private String bankName;
}
