package com.example.jdbcspringbootapp.conrtoller;

import com.example.jdbcspringbootapp.model.dto.request.transaction.*;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.transaction.*;
import com.example.jdbcspringbootapp.model.enums.Status;
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
            @RequestBody CreateTransactionReqDto createTransactionReqDto
    ) {
        ResponseDto<CreateTransactionRespDto> answer = new ResponseDto<>();
        answer.setData(transactionService.createTransaction(createTransactionReqDto));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }

    @GetMapping(value = "/get")//ex: /get/?id=8
    public ResponseEntity<ResponseDto<GetTransactionRespDto>> getTransactionById(
            @PathParam("id") Long id
    ) {
        ResponseDto<GetTransactionRespDto> answer = new ResponseDto<>();
        answer.setData(transactionService.getTransactionById(id));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }

    @GetMapping(value = "/get_first")
    public ResponseEntity<ResponseDto<GetFirstTransactionRespDto>> getFirstTransaction() {
        ResponseDto<GetFirstTransactionRespDto> answer = new ResponseDto<>();
        answer.setData(transactionService.getFirstTransaction());
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping(value = "/del")
    public ResponseEntity<ResponseDto<DeleteTransactionRespDto>> deleteTransactionById(
            @PathParam("id") Long id
    ) {
        ResponseDto<DeleteTransactionRespDto> answer = new ResponseDto<>();
        answer.setData(transactionService.deleteTransactionById(id));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }

    @PatchMapping(value = "/update", headers = "content-type=application/vnd.api+json")
    public ResponseEntity<ResponseDto<UpdateTransactionRespDto>> updateTransactionById(
            @PathParam("id") Long id,
            @RequestBody UpdateTransactionReqDto updateTransactionReqDto) {
        ResponseDto<UpdateTransactionRespDto> answer = new ResponseDto<>();
        answer.setData(transactionService.updateTransactionById(
                id, updateTransactionReqDto));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }
}