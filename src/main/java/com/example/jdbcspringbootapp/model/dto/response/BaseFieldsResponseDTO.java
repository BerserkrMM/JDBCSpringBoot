package com.example.jdbcspringbootapp.model.dto.response;

import com.example.jdbcspringbootapp.model.enums.IsDeleted;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class BaseFieldsResponseDTO {
    private IsDeleted is_deleted;
    private Long id;
    private UUID guid;
    private String created_time;
    private String modified_time;
}
