package com.example.jdbcspringbootapp.model.mappers;

import com.example.jdbcspringbootapp.model.dto.request.cardRequests.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.response.cardResponses.CreateCardRespDto;
import com.example.jdbcspringbootapp.model.entity.CardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardEntMapper {
    CardEntity sourceToDestination(CreateCardReqDto source);

    CreateCardRespDto destinationToSource(CardEntity destination);

}
