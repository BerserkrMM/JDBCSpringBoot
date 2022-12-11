package com.example.jdbcspringbootapp.repository.transactionCategoryRepository;

import com.example.jdbcspringbootapp.model.dto.request.transactionCategoriesRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.tranactionCategoriesResponses.*;

import java.util.Optional;

public interface TransactionCategoryRepository {

    Optional<CreateTransactionCategoryRespDto> createTransactionCategory(CreateTransactionCategoryReqDto transactionCategoryEntity);

    Optional<GetTransactionCategoryRespDto> getTransactionCategoryById(Long id);

    Optional<GetFirstTransactionCategoryRespDto> getFirstTransactionCategory();

    Optional<DeleteTransactionCategoryRespDto> deleteTransactionCategoryById(Long id);

    Optional<UpdateTransactionCategoryRespDto> updateTransactionCategoryById(Long id, UpdateTransactionCategoryReqDto updateTransactionCategoryReqDto);
}
