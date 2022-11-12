package com.example.jdbcspringbootapp.service;

import com.example.jdbcspringbootapp.model.dto.request.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.response.CreateCardRespDto;

public interface CardService {

    public CreateCardRespDto createCard(CreateCardReqDto createCardReqDto);
}
