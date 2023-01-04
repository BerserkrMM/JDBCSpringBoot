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
        return ResponseEntity.ok(currencyService.getCurrencyById(id));
    }

    @GetMapping(value = "/getFirstCurrency")
    public ResponseEntity<ResponseDto<GetFirstCurrencyRespDto>> getFirstCurrency() {
        return ResponseEntity.ok(currencyService.getFirstCurrency());
    }

    @DeleteMapping(value = "/del")
    public ResponseEntity<ResponseDto<DeleteCurrencyRespDto>> deleteCurrencyById(@PathParam("id") Long id) throws IllegalAccessException {
        return ResponseEntity.ok(currencyService.deleteCurrencyById(id));
    }

    @PatchMapping(value = "/update", headers = "content-type=application/vnd.api+json")
    public ResponseEntity<ResponseDto<UpdateCurrencyRespDto>> updateCurrencyById(
            @PathParam("id") Long id,
            @RequestBody UpdateCurrencyReqDto updateCurrencyReqDto) throws IllegalAccessException {
        return ResponseEntity.ok(currencyService.updateCurrencyById(id, updateCurrencyReqDto));
    }
}