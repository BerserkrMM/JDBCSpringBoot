package com.example.jdbcspringbootapp.conrtoller;

import com.example.jdbcspringbootapp.model.dto.request.cardRequests.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.request.cardRequests.UpdateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.cardResponses.*;
import com.example.jdbcspringbootapp.model.enums.Status;
import com.example.jdbcspringbootapp.service.cardService.CardService;
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
    public ResponseEntity<ResponseDto<CreateCardRespDto>> createCard(@RequestBody CreateCardReqDto createCardReqDto) {
        ResponseDto<CreateCardRespDto> answer = new ResponseDto<>();
        answer.setData(cardService.createCard(createCardReqDto));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }

    @ApiOperation("getCardById.")
    @GetMapping(value = "/get")//ex: /get/?id=8
    public ResponseEntity<ResponseDto<GetCardRespDto>> getCardById(@PathParam("id") Long id) {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(cardService.getCardById(id));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }

    //TODO: getFirstCard()
    @ApiOperation("getFirstCard.")
    @GetMapping(value = "/getFirstCard")
    public ResponseEntity<ResponseDto<GetFirstCardRespDto>> getFirstCard() {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(cardService.getFirstCard());
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }

    //TODO: deleteCardById(id)
    @ApiOperation("deleteCardById.")
    @DeleteMapping(value = "/del")
    public ResponseEntity<ResponseDto<DeleteCardRespDto>> deleteCardById(@PathParam("id") Long id) {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(cardService.deleteCardById(id));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }

    //TODO: updateCardById(updateCardRequestDTO)
    @ApiOperation("updateCardById.")
    @PatchMapping(value = "/update", headers = "content-type=application/vnd.api+json")
    public ResponseEntity<ResponseDto<UpdateCardRespDto>> updateCardById(
            @PathParam("id") Long id,
            @RequestBody UpdateCardReqDto updateCardReqDto) {
        ResponseDto answer = new ResponseDto<>();
        answer.setData(cardService.updateCardById(id, updateCardReqDto));
        answer.setStatus(Status.OK);
        return ResponseEntity.ok(answer);
    }
    //TODO: namedParametersJDBCTemplate VS JDBCTemplate read about!!!
    //named can add parameters with no sequence
}