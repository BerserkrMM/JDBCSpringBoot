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
            @RequestBody CreateTransactionCategoryReqDto createTransactionCategoryReqDto
    ) {
        ResponseDto<CreateTransactionCategoryRespDto> answer = new ResponseDto<>();
        answer.setData(transactionCategoryService.createTransactionCategory(createTransactionCategoryReqDto));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }

    @GetMapping(value = "/get")//ex: /get/?id=8
    public ResponseEntity<ResponseDto<GetTransactionCategoryRespDto>> getTransactionCategoryById(
            @PathParam("id") Long id
    ) {
        ResponseDto<GetTransactionCategoryRespDto> answer = new ResponseDto<>();
        answer.setData(transactionCategoryService.getTransactionCategoryById(id));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }

    @GetMapping(value = "/get_first_tr_category")
    public ResponseEntity<ResponseDto<GetFirstTransactionCategoryRespDto>> getFirstTransactionCategory() {
        ResponseDto<GetFirstTransactionCategoryRespDto> answer = new ResponseDto<>();
        answer.setData(transactionCategoryService.getFirstTransactionCategory());
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping(value = "/del")
    public ResponseEntity<ResponseDto<DeleteTransactionCategoryRespDto>> deleteTransactionCategoryById(
            @PathParam("id") Long id
    ) {
        ResponseDto<DeleteTransactionCategoryRespDto> answer = new ResponseDto<>();
        answer.setData(transactionCategoryService.deleteTransactionCategoryById(id));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }

    @PatchMapping(value = "/update", headers = "content-type=application/vnd.api+json")
    public ResponseEntity<ResponseDto<UpdateTransactionCategoryRespDto>> updateTransactionCategoryById(
            @PathParam("id") Long id,
            @RequestBody UpdateTransactionCategoryReqDto updateTransactionCategoryReqDto) {
        ResponseDto<UpdateTransactionCategoryRespDto> answer = new ResponseDto<>();
        answer.setData(transactionCategoryService.updateTransactionCategoryById(
                id, updateTransactionCategoryReqDto));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }
}