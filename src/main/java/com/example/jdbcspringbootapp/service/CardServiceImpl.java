package com.example.jdbcspringbootapp.service;

import com.example.jdbcspringbootapp.model.dto.request.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.response.CreateCardRespDto;
import com.example.jdbcspringbootapp.model.entity.CardEntity;
import com.example.jdbcspringbootapp.model.mappers.CardEntMapper;
import com.example.jdbcspringbootapp.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CardServiceImpl implements CardService {
    private final CardRepository repository;

    private final CardEntMapper mapper;

    public CreateCardRespDto createCard(CreateCardReqDto createCardReqDto) {

        repository.save(createCardReqDto);

        return new CreateCardRespDto();
    }

}