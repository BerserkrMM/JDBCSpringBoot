package com.example.jdbcspringbootapp.service.card;

import com.example.jdbcspringbootapp.model.dto.request.cardr.*;
import com.example.jdbcspringbootapp.model.dto.response.card.*;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

    CreateCardRespDto createCard(CreateCardReqDto createCardReqDto);
    GetCardRespDto getCardById(Long id);
    GetFirstCardRespDto getFirstCard();
    DeleteCardRespDto deleteCardById(Long id);
    UpdateCardRespDto updateCardById(Long id, UpdateCardReqDto updateCardReqDto);
}
