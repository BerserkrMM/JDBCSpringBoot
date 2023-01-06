package com.example.jdbcspringbootapp.repository.card;

import com.example.jdbcspringbootapp.model.dto.request.cardr.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.request.cardr.UpdateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.response.card.*;
import com.example.jdbcspringbootapp.repository.utils.JdbcExceptionWrappingRepository;

import java.util.Optional;

public interface CardRepository extends JdbcExceptionWrappingRepository {

    Optional<CreateCardRespDto> createCard(CreateCardReqDto cardEntity);
    Optional<GetCardRespDto> getCardById(Long id);
    Optional<GetFirstCardRespDto> getFirstCard();
    Optional<DeleteCardRespDto> deleteCardById(Long id);
    Optional<UpdateCardRespDto> updateCardById(Long id, UpdateCardReqDto updateCardReqDto);
    <C> Optional<C> isPresentByName(String cardName, Class<C> responseClass);
    <C> Optional<C> isPresentById(Long id, Class<C> responseClass);
}
