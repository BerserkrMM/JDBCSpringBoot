package com.example.jdbcspringbootapp.service.cardService;

import com.example.jdbcspringbootapp.model.dto.request.cardRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.cardResponses.*;
import com.example.jdbcspringbootapp.repository.cardRepository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository repository;

    @Override
    public CreateCardRespDto createCard(CreateCardReqDto createCardReqDto) {
        return repository.createCard(createCardReqDto).get();
    }

    @Override
    public GetCardRespDto getCardById(Long id) {
        return repository.getCardById(id).get();
    }

    @Override
    public GetFirstCardRespDto getFirstCard() {
        return repository.getFirstCard().get();
    }

    @Override
    public DeleteCardRespDto deleteCardById(Long id) {
        return repository.deleteCardById(id).get();
    }

    @Override
    public UpdateCardRespDto updateCardById(Long id, UpdateCardReqDto updateCardReqDto) {
        return repository.updateCardById(id, updateCardReqDto).get();
    }

}