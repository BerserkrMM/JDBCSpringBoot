package com.example.jdbcspringbootapp.service.card;

import com.example.jdbcspringbootapp.model.dto.request.cardr.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.request.cardr.UpdateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.card.*;
import com.example.jdbcspringbootapp.repository.card.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository repository;

    @Override
    public ResponseDto<CreateCardRespDto> createCard(CreateCardReqDto createCardReqDto) throws IllegalAccessException
    {
        if (repository.isPresentByName(createCardReqDto.getName(), CreateCardRespDto.class).isPresent()) {
            throw new IllegalAccessException("Card already exist.");
        }
        Optional<CreateCardRespDto> optionalAnswer = repository.createCard(createCardReqDto);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer,"Card is not created.");
    }

    @Override
    public ResponseDto<GetCardRespDto> getCardById(Long id)
    {
        Optional<GetCardRespDto> optionalAnswer = repository.getCardById(id);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer,"Card not found.");
    }

    @Override
    public ResponseDto<GetFirstCardRespDto> getFirstCard()
    {
        Optional<GetFirstCardRespDto> optionalAnswer = repository.getFirstCard();
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer,"Card not found.");
    }

    @Override
    public ResponseDto<DeleteCardRespDto> deleteCardById(Long id) throws IllegalAccessException
    {
        if(repository.isPresentById(id,DeleteCardRespDto.class).isEmpty()){
            throw new IllegalAccessException("Card with id '"+id+"' does not exist.");
        }
        Optional<DeleteCardRespDto> optionalAnswer = repository.deleteCardById(id);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer, "Card not found");
    }

    @Override
    public ResponseDto<UpdateCardRespDto> updateCardById(Long id, UpdateCardReqDto updateCardReqDto) throws IllegalAccessException
    {
        if(repository.isPresentById(id,UpdateCardRespDto.class).isEmpty()){
            throw new IllegalAccessException("Card with id '"+id+"' does not exist.");
        }
        Optional<UpdateCardRespDto> optionalAnswer = repository.updateCardById(id,updateCardReqDto);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer, "Card not found");
    }
}