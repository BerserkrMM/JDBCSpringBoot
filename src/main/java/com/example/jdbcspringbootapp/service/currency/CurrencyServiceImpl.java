package com.example.jdbcspringbootapp.service.currency;

import com.example.jdbcspringbootapp.model.dto.request.currency.CreateCurrencyReqDto;
import com.example.jdbcspringbootapp.model.dto.request.currency.UpdateCurrencyReqDto;
import com.example.jdbcspringbootapp.model.dto.response.ErrorDto;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.currency.*;
import com.example.jdbcspringbootapp.model.enums.Status;
import com.example.jdbcspringbootapp.repository.currency.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository repository;

    @Override
    public ResponseDto<CreateCurrencyRespDto> createCurrency(CreateCurrencyReqDto createCurrencyReqDto) throws IllegalAccessException {
        ResponseDto<CreateCurrencyRespDto> answer = new ResponseDto<>();

        if (repository.tryExistenceByCode(createCurrencyReqDto.getCode()).isPresent()) {
            throw new IllegalAccessException("Currency with this code already exist.");
        }

        var insertedEnt = repository.createCurrency(createCurrencyReqDto);

        if (insertedEnt.isEmpty()) {
            answer.setStatus(Status.FAILED);
            answer.setErrors(List.of(new ErrorDto("Currency is not created.")));
        } else {
            answer.setStatus(Status.OK);
            answer.setData(insertedEnt.get());
        }
        return answer;
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