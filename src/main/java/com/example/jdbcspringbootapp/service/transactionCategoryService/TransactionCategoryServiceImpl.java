package com.example.jdbcspringbootapp.service.transactionCategoryService;

import com.example.jdbcspringbootapp.model.dto.request.transactionCategoriesRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.tranactionCategoriesResponses.*;
import com.example.jdbcspringbootapp.repository.transactionCategoryRepository.TransactionCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionCategoryServiceImpl implements TransactionCategoryService {
    private final TransactionCategoryRepository repository;

    @Override
    public CreateTransactionCategoryRespDto createTransactionCategory(
            CreateTransactionCategoryReqDto createTransactionCategoryReqDto
    ) {
        return repository.createTransactionCategory(createTransactionCategoryReqDto).get();
    }

    @Override
    public GetTransactionCategoryRespDto getTransactionCategoryById(Long id) {
        return repository.getTransactionCategoryById(id).get();
    }

    @Override
    public GetFirstTransactionCategoryRespDto getFirstTransactionCategory() {
        return repository.getFirstTransactionCategory().get();
    }

    @Override
    public DeleteTransactionCategoryRespDto deleteTransactionCategoryById(Long id) {
        return repository.deleteTransactionCategoryById(id).get();
    }

    @Override
    public UpdateTransactionCategoryRespDto updateTransactionCategoryById(
            Long id, UpdateTransactionCategoryReqDto updateTransactionCategoryReqDto
    ) {
        return repository.updateTransactionCategoryById(id, updateTransactionCategoryReqDto).get();
    }

}