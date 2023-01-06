package com.example.jdbcspringbootapp.service.transactioncategory;

import com.example.jdbcspringbootapp.model.dto.request.transactioncategories.*;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.tranactioncategories.*;
import com.example.jdbcspringbootapp.repository.transactioncategory.TransactionCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionCategoryServiceImpl implements TransactionCategoryService {
    private final TransactionCategoryRepository repository;

    @Override
    public ResponseDto<CreateTransactionCategoryRespDto> createTransactionCategory(
            CreateTransactionCategoryReqDto createTransactionCategoryReqDto) throws IllegalAccessException
    {
        if (repository.isPresentByName(
                createTransactionCategoryReqDto.getName(), CreateTransactionCategoryRespDto.class).isPresent())
        {
            throw new IllegalAccessException(
                    "Category '"+createTransactionCategoryReqDto.getName()+"' already exist.");
        }
        Optional<CreateTransactionCategoryRespDto> optionalAnswer = repository.createTransactionCategory(createTransactionCategoryReqDto);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer,"Category not created");
    }

    @Override
    public ResponseDto<GetTransactionCategoryRespDto> getTransactionCategoryById(Long id)
    {
        Optional<GetTransactionCategoryRespDto> optionalAmswer = repository.getTransactionCategoryById(id);
        return setResponseIfOptionalIsOrNotEmpty(
                optionalAmswer,"Category with id '"+id+"' not found.");
    }

    @Override
    public ResponseDto<GetFirstTransactionCategoryRespDto> getFirstTransactionCategory()
    {
        Optional<GetFirstTransactionCategoryRespDto> optionalAmswer = repository.getFirstTransactionCategory();
        return setResponseIfOptionalIsOrNotEmpty(
                optionalAmswer,"Categories does not exist yet.");
    }

    @Override
    public ResponseDto<DeleteTransactionCategoryRespDto> deleteTransactionCategoryById(Long id) throws IllegalAccessException
    {
        if (repository.isPresentById(id, DeleteTransactionCategoryRespDto.class).isEmpty())
        {
            throw new IllegalAccessException("Category with id '"+id+"' not found.");
        }
        Optional<DeleteTransactionCategoryRespDto> optionalAnswer = repository.deleteTransactionCategoryById(id);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer,"Category not deleted");
    }

    @Override
    public ResponseDto<UpdateTransactionCategoryRespDto> updateTransactionCategoryById(
            Long id, UpdateTransactionCategoryReqDto updateTransactionCategoryReqDto) throws IllegalAccessException {
        if (repository.isPresentById(id, UpdateTransactionCategoryRespDto.class).isEmpty())
        {
            throw new IllegalAccessException("Category with id '"+id+"' not found.");
        }
        Optional<UpdateTransactionCategoryRespDto> optionalAnswer = repository
                .updateTransactionCategoryById(id,updateTransactionCategoryReqDto);
        return setResponseIfOptionalIsOrNotEmpty(optionalAnswer,"Category not updated");
    }

}