package com.example.jdbcspringbootapp.model.mappers;

import com.example.jdbcspringbootapp.model.dto.request.cardr.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.response.card.CreateCardRespDto;
import com.example.jdbcspringbootapp.model.entity.CardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardEntMapper {
    CardEntity sourceToDestination(CreateCardReqDto source);

    CreateCardRespDto destinationToSource(CardEntity destination);

}
