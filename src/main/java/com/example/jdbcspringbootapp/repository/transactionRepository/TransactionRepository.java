package com.example.jdbcspringbootapp.repository.transactionRepository;

import com.example.jdbcspringbootapp.model.dto.request.transactionRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.transactionResponses.*;

import java.util.Optional;

public interface TransactionRepository {

    Optional<CreateTransactionRespDto> createTransaction(CreateTransactionReqDto transactionEntity);

    Optional<GetTransactionRespDto> getTransactionById(Long id);

    Optional<GetFirstTransactionRespDto> getFirstTransaction();

    Optional<DeleteTransactionRespDto> deleteTransactionById(Long id);

    Optional<UpdateTransactionRespDto> updateTransactionById(Long id, UpdateTransactionReqDto updateTransactionReqDto);
}
