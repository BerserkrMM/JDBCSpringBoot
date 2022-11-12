package com.example.jdbcspringbootapp.model.dto.request;

import org.jetbrains.annotations.NotNull;
import java.math.BigDecimal;

public class CreateCardReqDto {
    @NotNull
    private BigDecimal amount = BigDecimal.valueOf(0); //set ZERO by default!!!

    @NotNull
    private Long currencyId;

    @NotNull
    private String simpleName = "Card " + this.hashCode();

    @NotNull
    private String bankName;
}
