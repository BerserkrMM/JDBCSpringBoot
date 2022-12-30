package com.example.jdbcspringbootapp.service.card;

import com.example.jdbcspringbootapp.model.dto.request.cardr.*;
import com.example.jdbcspringbootapp.model.dto.response.card.*;
import com.example.jdbcspringbootapp.repository.card.CardRepository;
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
        var ent = repository.getCardById(id);
        return ent.orElse(null);
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