package com.example.jdbcspringbootapp.repository.cardRepository;

import com.example.jdbcspringbootapp.model.dto.request.cardRequests.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.request.cardRequests.UpdateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.response.cardResponses.*;

import java.util.Optional;

public interface CardRepository {

    Optional<CreateCardRespDto> createCard(CreateCardReqDto cardEntity);
    Optional<GetCardRespDto> getCardById(Long id);
    Optional<GetFirstCardRespDto> getFirstCard();
    Optional<DeleteCardRespDto> deleteCardById(Long id);
    Optional<UpdateCardRespDto> updateCardById(Long id, UpdateCardReqDto updateCardReqDto);
}
