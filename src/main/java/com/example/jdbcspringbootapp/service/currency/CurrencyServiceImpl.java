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
        ResponseDto<CreateCurrencyRespDto> answer = new ResponseDto<>();

        if (repository.tryExistenceByCode(createCurrencyReqDto.getCode()).isPresent()) {
            throw new IllegalAccessException("Currency with this code already exist.");//зупиниться чи піде далі???
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
    public ResponseDto<GetCurrencyRespDto> getCurrencyById(Long id) {
        ResponseDto<GetCurrencyRespDto> answer = new ResponseDto<>();
        Optional<GetCurrencyRespDto> optionalAnswer = repository.getCurrencyById(id);
        if (optionalAnswer.isEmpty()){
            answer.setStatus(Status.FAILED);
            answer.setErrors(List.of(new ErrorDto("No currency found."/*String.format("No currency with ID: ", id)*/)));
        }
        else {
            answer.setStatus(Status.OK);
            answer.setData(optionalAnswer.get());
        }
        return answer;
    }

    @Override
    public ResponseDto<GetFirstCurrencyRespDto> getFirstCurrency() {
        ResponseDto<GetFirstCurrencyRespDto> answer = new ResponseDto<>();
        Optional<GetFirstCurrencyRespDto> optionalAnswer = repository.getFirstCurrency();
        if (optionalAnswer.isEmpty()){
            answer.setStatus(Status.FAILED);
            answer.setErrors(List.of(new ErrorDto("No currency found."/*String.format("No currency with ID: ", id)*/)));
        }
        else {
            answer.setStatus(Status.OK);
            answer.setData(optionalAnswer.get());
        }
        return answer;
    }

    @Override
    public ResponseDto<DeleteCurrencyRespDto> deleteCurrencyById(Long id) throws IllegalAccessException {
        ResponseDto<DeleteCurrencyRespDto> answer = new ResponseDto<>();

        if (repository.tryExistenceById(id).isEmpty()) {
            throw new IllegalAccessException("Currency does not exist.");
        }

        var optionalDeletedEntity = repository.deleteCurrencyById(id);

        if (optionalDeletedEntity.isEmpty()) {
            answer.setStatus(Status.FAILED);
            answer.setErrors(List.of(new ErrorDto("Currency is not deleted.")));
        } else {
            answer.setStatus(Status.OK);
            answer.setData(optionalDeletedEntity.get());
        }
        return answer;
    }

    @Override
    public ResponseDto<UpdateCurrencyRespDto> updateCurrencyById(Long id, UpdateCurrencyReqDto updateCurrencyReqDto) throws IllegalAccessException {
        ResponseDto<UpdateCurrencyRespDto> answer = new ResponseDto<>();

        if (repository.tryExistenceById(id).isEmpty()) {
            throw new IllegalAccessException("Currency does not exist.");
        }

        var optionalUpdatedEntity = repository.updateCurrencyById(id, updateCurrencyReqDto);

        if (optionalUpdatedEntity.isEmpty()) {
            answer.setStatus(Status.FAILED);
            answer.setErrors(List.of(new ErrorDto("Currency is not updated.")));
        } else {
            answer.setStatus(Status.OK);
            answer.setData(optionalUpdatedEntity.get());
        }
        return answer;
    }
}