package com.example.jdbcspringbootapp.conrtoller;

import com.example.jdbcspringbootapp.model.dto.request.transaction.CreateTransactionReqDto;
import com.example.jdbcspringbootapp.model.dto.request.transaction.UpdateTransactionReqDto;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.transaction.*;
import com.example.jdbcspringbootapp.service.transaction.TransactionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Api(tags = "Transactions")
@RestController
@RequestMapping(value = "/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private static final String V_1 = "application/vnd.jdbcspringbootapp.v_1+json";

    private final TransactionService transactionService;

    @PostMapping(value = "/create", produces = {V_1})
    public ResponseEntity<ResponseDto<CreateTransactionRespDto>> createTransaction(
            @RequestBody CreateTransactionReqDto createTransactionReqDto) {
        return ResponseEntity.ok(transactionService.createTransaction(createTransactionReqDto));
    }

    @GetMapping(value = "/get")//ex: /get/?id=8
    public ResponseEntity<ResponseDto<GetTransactionRespDto>> getTransactionById(@PathParam("id") Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @GetMapping(value = "/get_first")
    public ResponseEntity<ResponseDto<GetFirstTransactionRespDto>> getFirstTransaction() {
        return ResponseEntity.ok(transactionService.getFirstTransaction());
    }

    @DeleteMapping(value = "/del")
    public ResponseEntity<ResponseDto<DeleteTransactionRespDto>> deleteTransactionById(
            @PathParam("id") Long id) throws IllegalAccessException {
        return ResponseEntity.ok(transactionService.deleteTransactionById(id));
    }

    @PatchMapping(value = "/update", headers = "content-type=application/vnd.api+json")
    public ResponseEntity<ResponseDto<UpdateTransactionRespDto>> updateTransactionById(
            @PathParam("id") Long id,
            @RequestBody UpdateTransactionReqDto updateTransactionReqDto) throws IllegalAccessException {
        return ResponseEntity.ok(transactionService.updateTransactionById(id, updateTransactionReqDto));
    }
}