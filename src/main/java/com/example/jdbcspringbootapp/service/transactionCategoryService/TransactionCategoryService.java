package com.example.jdbcspringbootapp.service.transactionCategoryService;

import com.example.jdbcspringbootapp.model.dto.request.transactionCategoriesRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.tranactionCategoriesResponses.*;

public interface TransactionCategoryService {

    CreateTransactionCategoryRespDto createTransactionCategory(CreateTransactionCategoryReqDto createTransactionCategoryReqDto);
    GetTransactionCategoryRespDto getTransactionCategoryById(Long id);
    GetFirstTransactionCategoryRespDto getFirstTransactionCategory();
    DeleteTransactionCategoryRespDto deleteTransactionCategoryById(Long id);
    UpdateTransactionCategoryRespDto updateTransactionCategoryById(Long id, UpdateTransactionCategoryReqDto updateTransactionCategoryReqDto);
}
