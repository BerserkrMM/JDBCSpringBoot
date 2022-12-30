package com.example.jdbcspringbootapp.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    //    @ApiModelProperty("Error message.")
    private String message;
}
