package com.example.jdbcspringbootapp.conrtoller;

import com.example.jdbcspringbootapp.model.dto.request.transactionCategoriesRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.tranactionCategoriesResponses.*;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.enums.STATUS;
import com.example.jdbcspringbootapp.service.transactionCategoryService.TransactionCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    @GetMapping(value = "/get")//ex: /get/?id=8
    public ResponseEntity<ResponseDto<GetTransactionCategoryRespDto>> getTransactionCategoryById(
            @PathParam("id") Long id
    ) {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(transactionCategoryService.getTransactionCategoryById(id));
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    //TODO: getFirstTransactionCategory()
    @GetMapping(value = "/get_first_tr_category")
    public ResponseEntity<ResponseDto<GetFirstTransactionCategoryRespDto>> getFirstTransactionCategory() {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(transactionCategoryService.getFirstTransactionCategory());
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    //TODO: deleteTransactionCategoryById(id)
    @DeleteMapping(value = "/del")
    public ResponseEntity<ResponseDto<DeleteTransactionCategoryRespDto>> deleteTransactionCategoryById(
            @PathParam("id") Long id
    ) {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(transactionCategoryService.deleteTransactionCategoryById(id));
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    //TODO: updateTransactionCategoryById(updateTransactionCategoryRequestDTO)
    @PatchMapping(value = "/update", headers = "content-type=application/vnd.api+json")
    public ResponseEntity<ResponseDto<UpdateTransactionCategoryRespDto>> updateTransactionCategoryById(
            @PathParam("id") Long id,
            @RequestBody UpdateTransactionCategoryReqDto updateTransactionCategoryReqDto) {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(transactionCategoryService.updateTransactionCategoryById(
                id, updateTransactionCategoryReqDto));
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }
    //TODO: namedParametersJDBCTemplate VS JDBCTemplate read about!!!
    //named can add parameters with no sequence
}