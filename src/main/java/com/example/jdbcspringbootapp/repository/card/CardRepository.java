package com.example.jdbcspringbootapp.repository.card;

import com.example.jdbcspringbootapp.model.dto.request.cardr.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.request.cardr.UpdateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.response.card.*;

import java.util.Optional;

public interface CardRepository {

    Optional<CreateCardRespDto> createCard(CreateCardReqDto cardEntity);
    Optional<GetCardRespDto> getCardById(Long id);
    Optional<GetFirstCardRespDto> getFirstCard();
    Optional<DeleteCardRespDto> deleteCardById(Long id);
    Optional<UpdateCardRespDto> updateCardById(Long id, UpdateCardReqDto updateCardReqDto);
}
