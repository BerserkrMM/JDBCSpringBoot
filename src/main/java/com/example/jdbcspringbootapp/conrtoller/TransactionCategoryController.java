package com.example.jdbcspringbootapp.conrtoller;

import com.example.jdbcspringbootapp.model.dto.request.transactioncategories.*;
import com.example.jdbcspringbootapp.model.dto.response.tranactioncategories.*;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.enums.Status;
import com.example.jdbcspringbootapp.service.transactioncategory.TransactionCategoryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Api(tags = "Transaction Categories")
@RestController
@RequestMapping(value = "/tr_category")
@RequiredArgsConstructor
public class TransactionCategoryController {

    private static final String V_1 = "application/vnd.jdbcspringbootapp.v_1+json";

    private final TransactionCategoryService transactionCategoryService;

    @PostMapping(value = "/create", produces = {V_1})
    public ResponseEntity<ResponseDto<CreateTransactionCategoryRespDto>> createTransactionCategory(
            @RequestBody CreateTransactionCategoryReqDto createTransactionCategoryReqDto) throws IllegalAccessException
    {
        return ResponseEntity.ok(transactionCategoryService.createTransactionCategory(createTransactionCategoryReqDto));
    }

    @GetMapping(value = "/get")//ex: /get/?id=8
    public ResponseEntity<ResponseDto<GetTransactionCategoryRespDto>> getTransactionCategoryById(
            @PathParam("id") Long id)
    {
        return ResponseEntity.ok(transactionCategoryService.getTransactionCategoryById(id));
    }

    @GetMapping(value = "/get_first_tr_category")
    public ResponseEntity<ResponseDto<GetFirstTransactionCategoryRespDto>> getFirstTransactionCategory()
    {
        return ResponseEntity.ok(transactionCategoryService.getFirstTransactionCategory());
    }

    @DeleteMapping(value = "/del")
    public ResponseEntity<ResponseDto<DeleteTransactionCategoryRespDto>> deleteTransactionCategoryById(
            @PathParam("id") Long id) throws IllegalAccessException {
        return ResponseEntity.ok(transactionCategoryService.deleteTransactionCategoryById(id));
    }

    @PatchMapping(value = "/update", headers = "content-type=application/vnd.api+json")
    public ResponseEntity<ResponseDto<UpdateTransactionCategoryRespDto>> updateTransactionCategoryById(
            @PathParam("id") Long id,
            @RequestBody UpdateTransactionCategoryReqDto updateTransactionCategoryReqDto) throws IllegalAccessException
    {
        return ResponseEntity.ok(transactionCategoryService.updateTransactionCategoryById(
                id, updateTransactionCategoryReqDto));
    }
}