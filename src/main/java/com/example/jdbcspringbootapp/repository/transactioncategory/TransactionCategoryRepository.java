package com.example.jdbcspringbootapp.repository.transactioncategory;

import com.example.jdbcspringbootapp.model.dto.request.transactioncategories.*;
import com.example.jdbcspringbootapp.model.dto.response.tranactioncategories.*;
import com.example.jdbcspringbootapp.repository.utils.JdbcExceptionWrappingRepository;

import java.util.Optional;

public interface TransactionCategoryRepository extends JdbcExceptionWrappingRepository {

    Optional<CreateTransactionCategoryRespDto> createTransactionCategory(CreateTransactionCategoryReqDto transactionCategoryEntity);

    Optional<GetTransactionCategoryRespDto> getTransactionCategoryById(Long id);

    Optional<GetFirstTransactionCategoryRespDto> getFirstTransactionCategory();

    Optional<DeleteTransactionCategoryRespDto> deleteTransactionCategoryById(Long id);

    Optional<UpdateTransactionCategoryRespDto> updateTransactionCategoryById(Long id, UpdateTransactionCategoryReqDto updateTransactionCategoryReqDto);
    <C> Optional<C> isPresentByName(String categoryName, Class<C> responseClassToMapOn);
    <C> Optional<C> isPresentById(Long id, Class<C> responseClassToMapOn);
}
