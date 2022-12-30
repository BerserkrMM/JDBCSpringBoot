package com.example.jdbcspringbootapp.service.currencyService;

import com.example.jdbcspringbootapp.model.dto.request.currencyRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.currencyResponses.*;

public interface CurrencyService {

    CreateCurrencyRespDto createCurrency(CreateCurrencyReqDto createCurrencyReqDto) throws IllegalAccessException;
    GetCurrencyRespDto getCurrencyById(Long id);
    GetFirstCurrencyRespDto getFirstCurrency();
    DeleteCurrencyRespDto deleteCurrencyById(Long id);
    UpdateCurrencyRespDto updateCurrencyById(Long id, UpdateCurrencyReqDto updateCurrencyReqDto);
}
