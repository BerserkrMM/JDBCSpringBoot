package com.example.jdbcspringbootapp.conrtoller;

import com.example.jdbcspringbootapp.model.dto.request.currency.CreateCurrencyReqDto;
import com.example.jdbcspringbootapp.model.dto.request.currency.UpdateCurrencyReqDto;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.currency.*;
import com.example.jdbcspringbootapp.model.enums.Status;
import com.example.jdbcspringbootapp.service.currency.CurrencyService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Api(tags = "Currencies")
@RestController
@RequestMapping(value = "/currency")
@RequiredArgsConstructor
public class CurrencyController {

    //read WTF is this:
    private static final String V_1 = "application/vnd.jdbcspringbootapp.v_1+json";

    private final CurrencyService currencyService;

    //Create only if no Currencies with income code in DB
    @PostMapping(value = "/create", produces = {V_1})
    public ResponseEntity<ResponseDto<CreateCurrencyRespDto>> createCurrency(@RequestBody CreateCurrencyReqDto createCurrencyReqDto) throws IllegalAccessException {
        return ResponseEntity.ok(currencyService.createCurrency(createCurrencyReqDto));
    }

    @GetMapping(value = "/get", produces = {V_1})//ex: /get/?id=8
    public ResponseEntity<ResponseDto<GetCurrencyRespDto>> getCurrencyById(@PathParam("id") Long id) {
        ResponseDto<GetCurrencyRespDto> answer = new ResponseDto<>();
        answer.setData(currencyService.getCurrencyById(id));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }


    @GetMapping(value = "/getFirstCurrency")
    public ResponseEntity<ResponseDto<GetFirstCurrencyRespDto>> getFirstCurrency() {
        ResponseDto<GetFirstCurrencyRespDto> answer = new ResponseDto<>();
        answer.setData(currencyService.getFirstCurrency());
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }


    @DeleteMapping(value = "/del")
    public ResponseEntity<ResponseDto<DeleteCurrencyRespDto>> deleteCurrencyById(@PathParam("id") Long id) {
        ResponseDto<DeleteCurrencyRespDto> answer = new ResponseDto<>();
        answer.setData(currencyService.deleteCurrencyById(id));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }


    @PatchMapping(value = "/update", headers = "content-type=application/vnd.api+json")
    public ResponseEntity<ResponseDto<UpdateCurrencyRespDto>> updateCurrencyById(
            @PathParam("id") Long id,
            @RequestBody UpdateCurrencyReqDto updateCurrencyReqDto) {
        ResponseDto<UpdateCurrencyRespDto> answer = new ResponseDto<>();
        answer.setData(currencyService.updateCurrencyById(id, updateCurrencyReqDto));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }
}