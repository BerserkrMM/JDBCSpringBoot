package com.example.jdbcspringbootapp.service.currency;

import com.example.jdbcspringbootapp.model.dto.request.currency.CreateCurrencyReqDto;
import com.example.jdbcspringbootapp.model.dto.request.currency.UpdateCurrencyReqDto;
import com.example.jdbcspringbootapp.model.dto.response.ErrorDto;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.currency.*;
import com.example.jdbcspringbootapp.model.enums.Status;
import com.example.jdbcspringbootapp.repository.currency.CurrencyRepository;
import io.swagger.models.properties.ObjectProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository repository;

    @Override
    public ResponseDto<CreateCurrencyRespDto> createCurrency(CreateCurrencyReqDto createCurrencyReqDto) throws IllegalAccessException {
        if (repository.isPresentByCode(createCurrencyReqDto.getCode(), CreateCurrencyRespDto.class).isPresent()) {
            throw new IllegalAccessException("Currency with code '"+createCurrencyReqDto.getCode()+"' already exist.");//зупиниться чи піде далі???
        }
        if (repository.isPresentByName(createCurrencyReqDto.getName(),CreateCurrencyRespDto.class).isPresent()) {
            throw new IllegalAccessException("Currency with name '"+createCurrencyReqDto.getName()+"' already exist.");//зупиниться чи піде далі???
        }
        Optional<CreateCurrencyRespDto> optionalAnswer = repository.createCurrency(createCurrencyReqDto);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer, "Currency is not created.");
    }

    @Override
    public ResponseDto<GetCurrencyRespDto> getCurrencyById(Long id) {
        Optional<GetCurrencyRespDto> optionalAnswer = repository.getCurrencyById(id);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer, "No currency found.");
    }

    @Override
    public ResponseDto<GetFirstCurrencyRespDto> getFirstCurrency() {
        Optional<GetFirstCurrencyRespDto> optionalAnswer = repository.getFirstCurrency();
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer, "No currency found.");
    }

    @Override
    public ResponseDto<DeleteCurrencyRespDto> deleteCurrencyById(Long id) throws IllegalAccessException {
        if (repository.isPresentById(id, DeleteCurrencyRespDto.class).isEmpty()) {
            throw new IllegalAccessException("Currency does not exist.");
        }
        Optional<DeleteCurrencyRespDto> optionalAnswer = repository.deleteCurrencyById(id);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer, "Currency is not deleted.");
    }

    @Override
    public ResponseDto<UpdateCurrencyRespDto> updateCurrencyById(Long id, UpdateCurrencyReqDto updateCurrencyReqDto) throws IllegalAccessException {
        if (repository.isPresentById(id, UpdateCurrencyRespDto.class).isEmpty()) {
            throw new IllegalAccessException("Currency does not exist.");
        }
        Optional<UpdateCurrencyRespDto> optionalAnswer = repository.updateCurrencyById(id, updateCurrencyReqDto);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer, "Currency is not updated.");
    }
}