package com.example.jdbcspringbootapp.repository.transaction;

import com.example.jdbcspringbootapp.model.dto.request.transaction.*;
import com.example.jdbcspringbootapp.model.dto.response.transaction.*;

import java.util.Optional;

public interface TransactionRepository {

    Optional<CreateTransactionRespDto> createTransaction(CreateTransactionReqDto transactionEntity);

    Optional<GetTransactionRespDto> getTransactionById(Long id);

    Optional<GetFirstTransactionRespDto> getFirstTransaction();

    Optional<DeleteTransactionRespDto> deleteTransactionById(Long id);

    Optional<UpdateTransactionRespDto> updateTransactionById(Long id, UpdateTransactionReqDto updateTransactionReqDto);
}
