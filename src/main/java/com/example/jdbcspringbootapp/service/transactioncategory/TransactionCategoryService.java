package com.example.jdbcspringbootapp.service.transactioncategory;

import com.example.jdbcspringbootapp.model.dto.request.transactioncategories.*;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.tranactioncategories.*;
import com.example.jdbcspringbootapp.service.utils.ResponseIfOptionalIsOrNotEmpty;

public interface TransactionCategoryService extends ResponseIfOptionalIsOrNotEmpty {

    ResponseDto<CreateTransactionCategoryRespDto> createTransactionCategory(CreateTransactionCategoryReqDto createTransactionCategoryReqDto) throws IllegalAccessException;
    ResponseDto<GetTransactionCategoryRespDto> getTransactionCategoryById(Long id);
    ResponseDto<GetFirstTransactionCategoryRespDto> getFirstTransactionCategory();
    ResponseDto<DeleteTransactionCategoryRespDto> deleteTransactionCategoryById(Long id) throws IllegalAccessException;
    ResponseDto<UpdateTransactionCategoryRespDto> updateTransactionCategoryById(Long id, UpdateTransactionCategoryReqDto updateTransactionCategoryReqDto) throws IllegalAccessException;
}
