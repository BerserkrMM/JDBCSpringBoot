package com.example.jdbcspringbootapp.service.cardService;

import com.example.jdbcspringbootapp.model.dto.request.cardRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.cardResponses.*;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

    CreateCardRespDto createCard(CreateCardReqDto createCardReqDto);
    GetCardRespDto getCardById(Long id);
    GetFirstCardRespDto getFirstCard();
    DeleteCardRespDto deleteCardById(Long id);
    UpdateCardRespDto updateCardById(Long id, UpdateCardReqDto updateCardReqDto);
}
