package com.example.jdbcspringbootapp.service.transactionService;

import com.example.jdbcspringbootapp.model.dto.request.transactionRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.transactionResponses.*;

public interface TransactionService {

    CreateTransactionRespDto createTransaction(CreateTransactionReqDto createTransactionReqDto);
    GetTransactionRespDto getTransactionById(Long id);
    GetFirstTransactionRespDto getFirstTransaction();
    DeleteTransactionRespDto deleteTransactionById(Long id);
    UpdateTransactionRespDto updateTransactionById(Long id, UpdateTransactionReqDto updateTransactionReqDto);
}
