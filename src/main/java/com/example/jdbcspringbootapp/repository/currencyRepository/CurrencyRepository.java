package com.example.jdbcspringbootapp.repository.currencyRepository;

import com.example.jdbcspringbootapp.model.dto.response.currencyResponses.*;
import com.example.jdbcspringbootapp.model.dto.request.currencyRequests.*;

import java.util.Optional;

public interface CurrencyRepository {

    Optional<CreateCurrencyRespDto> createCurrency(CreateCurrencyReqDto createCurrencyReqDto);
    Optional<GetCurrencyRespDto> getCurrencyById(Long id);
    Optional<GetFirstCurrencyRespDto> getFirstCurrency();
    Optional<DeleteCurrencyRespDto> deleteCurrencyById(Long id);
    Optional<UpdateCurrencyRespDto> updateCurrencyById(Long id, UpdateCurrencyReqDto updateCurrencyReqDto);
}
