package com.example.jdbcspringbootapp.conrtoller;

import com.example.jdbcspringbootapp.model.dto.request.transactionRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.transactionResponses.*;
import com.example.jdbcspringbootapp.model.enums.STATUS;
import com.example.jdbcspringbootapp.service.transactionService.TransactionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.ietf.jgss.Oid;
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
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    @GetMapping(value = "/get")//ex: /get/?id=8
    public ResponseEntity<ResponseDto<GetTransactionRespDto>> getTransactionById(
            @PathParam("id") Long id
    ) {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(transactionService.getTransactionById(id));
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    //TODO: getFirstTransaction()
    @GetMapping(value = "/get_first")
    public ResponseEntity<ResponseDto<GetFirstTransactionRespDto>> getFirstTransaction() {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(transactionService.getFirstTransaction());
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    //TODO: deleteTransactionById(id)
    @DeleteMapping(value = "/del")
    public ResponseEntity<ResponseDto<DeleteTransactionRespDto>> deleteTransactionById(
            @PathParam("id") Long id
    ) {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(transactionService.deleteTransactionById(id));
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    //TODO: updateTransactionById(updateTransactionRequestDTO)
    @PatchMapping(value = "/update", headers = "content-type=application/vnd.api+json")
    public ResponseEntity<ResponseDto<UpdateTransactionRespDto>> updateTransactionById(
            @PathParam("id") Long id,
            @RequestBody UpdateTransactionReqDto updateTransactionReqDto) {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(transactionService.updateTransactionById(
                id, updateTransactionReqDto));
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }
    //TODO: namedParametersJDBCTemplate VS JDBCTemplate read about!!!
    //named can add parameters with no sequence
}