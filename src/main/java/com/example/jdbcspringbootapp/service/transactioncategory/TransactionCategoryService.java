package com.example.jdbcspringbootapp.service.transactioncategory;

import com.example.jdbcspringbootapp.model.dto.request.transactioncategories.*;
import com.example.jdbcspringbootapp.model.dto.response.tranactioncategories.*;

public interface TransactionCategoryService {

    CreateTransactionCategoryRespDto createTransactionCategory(CreateTransactionCategoryReqDto createTransactionCategoryReqDto);
    GetTransactionCategoryRespDto getTransactionCategoryById(Long id);
    GetFirstTransactionCategoryRespDto getFirstTransactionCategory();
    DeleteTransactionCategoryRespDto deleteTransactionCategoryById(Long id);
    UpdateTransactionCategoryRespDto updateTransactionCategoryById(Long id, UpdateTransactionCategoryReqDto updateTransactionCategoryReqDto);
}
