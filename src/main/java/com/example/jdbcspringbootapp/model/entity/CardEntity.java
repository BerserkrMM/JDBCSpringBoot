package com.example.jdbcspringbootapp.model.entity;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
public class CardEntity {

    @Id
    private Long id;

    @NotNull
    private BigDecimal amount = BigDecimal.valueOf(0); //set ZERO by default!!!

    @NotNull
    private Long currency;

    private String cardName = "Card " + this.hashCode();

    @NotNull
    private String bankName;

    @Override
    public String toString(){
        return this.id+"\n"
                +this.amount+"\n"
                +this.currency+"\n"
                +this.cardName+"\n"
                +this.bankName;

    }

}
