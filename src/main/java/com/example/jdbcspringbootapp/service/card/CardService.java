package com.example.jdbcspringbootapp.service.card;

import com.example.jdbcspringbootapp.model.dto.request.cardr.*;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.card.*;
import com.example.jdbcspringbootapp.service.utils.ResponseIfOptionalIsOrNotEmpty;
import org.springframework.stereotype.Service;

@Service
public interface CardService extends ResponseIfOptionalIsOrNotEmpty {

    ResponseDto<CreateCardRespDto> createCard(CreateCardReqDto createCardReqDto) throws IllegalAccessException;
    ResponseDto<GetCardRespDto> getCardById(Long id);
    ResponseDto<GetFirstCardRespDto> getFirstCard();
    ResponseDto<DeleteCardRespDto> deleteCardById(Long id) throws IllegalAccessException;
    ResponseDto<UpdateCardRespDto> updateCardById(Long id, UpdateCardReqDto updateCardReqDto) throws IllegalAccessException;
}
