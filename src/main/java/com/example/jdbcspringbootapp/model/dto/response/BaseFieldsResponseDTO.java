package com.example.jdbcspringbootapp.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class BaseFieldsResponseDTO {
    private Long id;
    private UUID uuid;
    private String created_timestamp;
    private String modified_timestamp;
}
