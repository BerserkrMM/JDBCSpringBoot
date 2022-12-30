package com.example.jdbcspringbootapp.service.currency;

import com.example.jdbcspringbootapp.model.dto.request.currency.*;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.currency.*;

public interface CurrencyService {

    ResponseDto<CreateCurrencyRespDto> createCurrency(CreateCurrencyReqDto createCurrencyReqDto) throws IllegalAccessException;
    GetCurrencyRespDto getCurrencyById(Long id);
    GetFirstCurrencyRespDto getFirstCurrency();
    DeleteCurrencyRespDto deleteCurrencyById(Long id);
    UpdateCurrencyRespDto updateCurrencyById(Long id, UpdateCurrencyReqDto updateCurrencyReqDto);
}
