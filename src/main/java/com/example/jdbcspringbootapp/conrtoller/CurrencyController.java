package com.example.jdbcspringbootapp.conrtoller;

import com.example.jdbcspringbootapp.model.dto.request.currencyRequests.*;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.currencyResponses.*;
import com.example.jdbcspringbootapp.model.enums.STATUS;
import com.example.jdbcspringbootapp.service.currencyService.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Api(tags = "Currencies")
@RestController
@RequestMapping(value = "/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private static final String V_1 = "application/vnd.jdbcspringbootapp.v_1+json";

    private final CurrencyService currencyService;

    @ApiOperation()
    @PostMapping(value = "/create", produces = {V_1})
    public ResponseEntity<ResponseDto<CreateCurrencyRespDto>> createCurrency(@RequestBody CreateCurrencyReqDto createCurrencyReqDto) {
        ResponseDto<CreateCurrencyRespDto> answer = new ResponseDto<>();
        answer.setData(currencyService.createCurrency(createCurrencyReqDto));
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    @GetMapping(value = "/get")//ex: /get/?id=8
    public ResponseEntity<ResponseDto<GetCurrencyRespDto>> getCurrencyById(@PathParam("id") Long id) {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(currencyService.getCurrencyById(id));
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    //TODO: getFirstCurrency()
    @GetMapping(value = "/getFirstCurrency")
    public ResponseEntity<ResponseDto<GetFirstCurrencyRespDto>> getFirstCurrency() {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(currencyService.getFirstCurrency());
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    //TODO: deleteCurrencyById(id)
    @DeleteMapping(value = "/del")
    public ResponseEntity<ResponseDto<DeleteCurrencyRespDto>> deleteCurrencyById(@PathParam("id") Long id) {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(currencyService.deleteCurrencyById(id));
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    //TODO: updateCurrencyById(updateCurrencyRequestDTO)
    @PatchMapping(value = "/update", headers = "content-type=application/vnd.api+json")
    public ResponseEntity<ResponseDto<UpdateCurrencyRespDto>> updateCurrencyById(
            @PathParam("id") Long id,
            @RequestBody UpdateCurrencyReqDto updateCurrencyReqDto) {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(currencyService.updateCurrencyById(id, updateCurrencyReqDto));
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }
    //TODO: namedParametersJDBCTemplate VS JDBCTemplate read about!!!
    //named can add parameters with no sequence
}