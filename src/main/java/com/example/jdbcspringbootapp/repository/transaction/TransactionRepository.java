package com.example.jdbcspringbootapp.repository.transaction;

import com.example.jdbcspringbootapp.model.dto.request.transaction.*;
import com.example.jdbcspringbootapp.model.dto.response.transaction.*;
import com.example.jdbcspringbootapp.repository.utils.JdbcExceptionWrappingRepository;

import java.util.Optional;

public interface TransactionRepository extends JdbcExceptionWrappingRepository {

    Optional<CreateTransactionRespDto> createTransaction(CreateTransactionReqDto transactionEntity);

    Optional<GetTransactionRespDto> getTransactionById(Long id);

    Optional<GetFirstTransactionRespDto> getFirstTransaction();

    Optional<DeleteTransactionRespDto> deleteTransactionById(Long id);

    Optional<UpdateTransactionRespDto> updateTransactionById(Long id, UpdateTransactionReqDto updateTransactionReqDto);

    <C> Optional<C> isPresentById(Long id, Class<C> responseClassToMapOn);
}
