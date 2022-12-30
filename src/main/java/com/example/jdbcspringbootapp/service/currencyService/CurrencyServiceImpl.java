package com.example.jdbcspringbootapp.service.currencyService;

import com.example.jdbcspringbootapp.model.dto.request.currencyRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.currencyResponses.*;
import com.example.jdbcspringbootapp.repository.currencyRepository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository repository;

    @Override
    public CreateCurrencyRespDto createCurrency(CreateCurrencyReqDto createCurrencyReqDto) throws IllegalAccessException {
        //realise (return Optional<Currency/id> / boolean)
        //.orElseThrow(()->new IllegalAccessError("Currency with this code already exist."));

        if (repository.tryExistenceByCode(createCurrencyReqDto.getCode())) {
            throw new IllegalAccessException("Currency with this code already exist.");
        }
        return repository.createCurrency(createCurrencyReqDto).get();
    }

    @Override
    public GetCurrencyRespDto getCurrencyById(Long id) {
        return repository.getCurrencyById(id).get();
    }

    @Override
    public GetFirstCurrencyRespDto getFirstCurrency() {
        return repository.getFirstCurrency().get();
    }

    @Override
    public DeleteCurrencyRespDto deleteCurrencyById(Long id) {

        return repository.deleteCurrencyById(id).get();
    }

    @Override
    public UpdateCurrencyRespDto updateCurrencyById(Long id, UpdateCurrencyReqDto updateCurrencyReqDto) {
        return repository.updateCurrencyById(id, updateCurrencyReqDto).get();
    }

}