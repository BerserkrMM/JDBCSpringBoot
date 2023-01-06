package com.example.jdbcspringbootapp.repository.currency;

import com.example.jdbcspringbootapp.model.dto.response.currency.*;
import com.example.jdbcspringbootapp.model.dto.request.currency.*;
import com.example.jdbcspringbootapp.repository.utils.JdbcExceptionWrappingRepository;

import java.util.Optional;

public interface CurrencyRepository extends JdbcExceptionWrappingRepository {

    Optional<CreateCurrencyRespDto> createCurrency(CreateCurrencyReqDto createCurrencyReqDto);
    Optional<GetCurrencyRespDto> getCurrencyById(Long id);
    Optional<GetFirstCurrencyRespDto> getFirstCurrency();
    Optional<DeleteCurrencyRespDto> deleteCurrencyById(Long id);
    Optional<UpdateCurrencyRespDto> updateCurrencyById(Long id, UpdateCurrencyReqDto updateCurrencyReqDto);
    <C> Optional<C> isPresentById(Long id, Class<C> responseClassToMapOn);
    <C> Optional<C> isPresentByName(String cardName, Class<C> responseClassToMapOn);
    <C> Optional<C> isPresentByCode(String currencyCode, Class<C> responseClassToMapOn);
}
