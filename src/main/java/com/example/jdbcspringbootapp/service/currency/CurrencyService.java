package com.example.jdbcspringbootapp.service.currency;

import com.example.jdbcspringbootapp.model.dto.request.currency.*;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.currency.*;

public interface CurrencyService {

    ResponseDto<CreateCurrencyRespDto> createCurrency(CreateCurrencyReqDto createCurrencyReqDto) throws IllegalAccessException;
    ResponseDto<GetCurrencyRespDto> getCurrencyById(Long id);
    ResponseDto<GetFirstCurrencyRespDto> getFirstCurrency();
    ResponseDto<DeleteCurrencyRespDto> deleteCurrencyById(Long id) throws IllegalAccessException;
    ResponseDto<UpdateCurrencyRespDto> updateCurrencyById(Long id, UpdateCurrencyReqDto updateCurrencyReqDto) throws IllegalAccessException;
}
