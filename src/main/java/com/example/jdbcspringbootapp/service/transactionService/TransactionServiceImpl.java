package com.example.jdbcspringbootapp.service.transactionService;

import com.example.jdbcspringbootapp.model.dto.request.transactionRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.transactionResponses.*;
import com.example.jdbcspringbootapp.repository.transactionRepository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;

    @Override
    public CreateTransactionRespDto createTransaction(
            CreateTransactionReqDto createTransactionReqDto
    ) {
        return repository.createTransaction(createTransactionReqDto).get();
    }

    @Override
    public GetTransactionRespDto getTransactionById(Long id) {
        return repository.getTransactionById(id).get();
    }

    @Override
    public GetFirstTransactionRespDto getFirstTransaction() {
        return repository.getFirstTransaction().get();
    }

    @Override
    public DeleteTransactionRespDto deleteTransactionById(Long id) {
        return repository.deleteTransactionById(id).get();
    }

    @Override
    public UpdateTransactionRespDto updateTransactionById(
            Long id, UpdateTransactionReqDto updateTransactionReqDto
    ) {
        return repository.updateTransactionById(id, updateTransactionReqDto).get();
    }

}