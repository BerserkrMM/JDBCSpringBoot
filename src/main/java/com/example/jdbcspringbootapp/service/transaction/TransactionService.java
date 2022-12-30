package com.example.jdbcspringbootapp.service.transaction;

import com.example.jdbcspringbootapp.model.dto.request.transaction.*;
import com.example.jdbcspringbootapp.model.dto.response.transaction.*;

public interface TransactionService {

    CreateTransactionRespDto createTransaction(CreateTransactionReqDto createTransactionReqDto);
    GetTransactionRespDto getTransactionById(Long id);
    GetFirstTransactionRespDto getFirstTransaction();
    DeleteTransactionRespDto deleteTransactionById(Long id);
    UpdateTransactionRespDto updateTransactionById(Long id, UpdateTransactionReqDto updateTransactionReqDto);
}
