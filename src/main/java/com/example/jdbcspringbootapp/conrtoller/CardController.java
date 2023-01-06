package com.example.jdbcspringbootapp.conrtoller;

import com.example.jdbcspringbootapp.model.dto.request.cardr.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.request.cardr.UpdateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.card.*;
import com.example.jdbcspringbootapp.service.card.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Api(tags = "Cards")
@RestController
@RequestMapping(value = "/card")
@RequiredArgsConstructor
public class CardController {

    private static final String V_1 = "application/vnd.jdbcspringbootapp.v_1+json";

    private final CardService cardService;

    @ApiOperation("createCard.")
    @PostMapping(value = "/create", produces = {V_1})
    public ResponseEntity<ResponseDto<CreateCardRespDto>> createCard(@RequestBody CreateCardReqDto createCardReqDto) throws IllegalAccessException {
        return ResponseEntity.ok(cardService.createCard(createCardReqDto));
    }

    @ApiOperation("getCardById.")
    @GetMapping(value = "/get")//ex: /get/?id=8
    public ResponseEntity<ResponseDto<GetCardRespDto>> getCardById(@PathParam("id") Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @ApiOperation("getFirstCard.")
    @GetMapping(value = "/getFirstCard")
    public ResponseEntity<ResponseDto<GetFirstCardRespDto>> getFirstCard() {
        return ResponseEntity.ok(cardService.getFirstCard());
    }

    @ApiOperation("deleteCardById.")
    @DeleteMapping(value = "/del")
    public ResponseEntity<ResponseDto<DeleteCardRespDto>> deleteCardById(@PathParam("id") Long id) throws IllegalAccessException {
        return ResponseEntity.ok(cardService.deleteCardById(id));
    }

    @ApiOperation("updateCardById.")
    @PatchMapping(value = "/update", headers = "content-type=application/vnd.api+json")
    public ResponseEntity<ResponseDto<UpdateCardRespDto>> updateCardById(
            @PathParam("id") Long id,
            @RequestBody UpdateCardReqDto updateCardReqDto) throws IllegalAccessException {
        return ResponseEntity.ok(cardService.updateCardById(id, updateCardReqDto));
    }
}