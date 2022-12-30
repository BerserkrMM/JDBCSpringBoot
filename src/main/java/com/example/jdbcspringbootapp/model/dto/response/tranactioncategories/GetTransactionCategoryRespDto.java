package com.example.jdbcspringbootapp.model.dto.response.tranactioncategories;

import com.example.jdbcspringbootapp.model.dto.response.BaseFieldsResponseDTO;
import com.example.jdbcspringbootapp.model.enums.Transaction_Directions;
import lombok.*;
import org.springframework.stereotype.Service;

@Builder
@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionCategoryRespDto extends BaseFieldsResponseDTO {
    private Transaction_Directions type;
    private String name;
}
