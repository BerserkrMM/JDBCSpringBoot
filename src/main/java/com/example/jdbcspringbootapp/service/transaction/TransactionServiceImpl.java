package com.example.jdbcspringbootapp.service.transaction;

import com.example.jdbcspringbootapp.model.dto.request.transaction.CreateTransactionReqDto;
import com.example.jdbcspringbootapp.model.dto.request.transaction.UpdateTransactionReqDto;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.transaction.*;
import com.example.jdbcspringbootapp.repository.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;

    @Override
    public ResponseDto<CreateTransactionRespDto> createTransaction(CreateTransactionReqDto createTransactionReqDto) {
        Optional<CreateTransactionRespDto> optionalAnswer = repository.createTransaction(createTransactionReqDto);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer, "Transaction not created.");
    }

    @Override
    public ResponseDto<GetTransactionRespDto> getTransactionById(Long id) {
        Optional<GetTransactionRespDto> optionalAnswer = repository.getTransactionById(id);
        return setResponseIfOptionalIsOrNotEmpty(
                optionalAnswer, "Transaction with id '" + id + "' does not exist.");
    }

    @Override
    public ResponseDto<GetFirstTransactionRespDto> getFirstTransaction() {
        Optional<GetFirstTransactionRespDto> optionalAnswer = repository.getFirstTransaction();
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer, "Transaction does not exist");
    }

    @Override
    public ResponseDto<DeleteTransactionRespDto> deleteTransactionById(Long id) throws IllegalAccessException {
        if (repository.isPresentById(id, DeleteTransactionRespDto.class).isEmpty()) {
            throw new IllegalAccessException("Transaction with id '" + id + "' does not exist.");
        }
        Optional<DeleteTransactionRespDto> optionalAnswer = repository.deleteTransactionById(id);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer, "Transaction does not exist");
    }

    @Override
    public ResponseDto<UpdateTransactionRespDto> updateTransactionById(
            Long id, UpdateTransactionReqDto updateTransactionReqDto
    ) throws IllegalAccessException {
        if (repository.isPresentById(id, UpdateTransactionRespDto.class).isEmpty()) {
            throw new IllegalAccessException("Transaction with id '" + id + "' does not exist.");
        }
        Optional<UpdateTransactionRespDto> optionalAnswer = repository.updateTransactionById(id, updateTransactionReqDto);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer, "Transaction does not exist");
    }
}