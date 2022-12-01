package com.example.jdbcspringbootapp.conrtoller;

import com.example.jdbcspringbootapp.model.dto.request.CreateCardReqDto;
import com.example.jdbcspringbootapp.model.dto.response.CreateCardRespDto;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.entity.CardEntity;
import com.example.jdbcspringbootapp.model.enums.STATUS;
import com.example.jdbcspringbootapp.repository.CardRepositoryImpl;
import com.example.jdbcspringbootapp.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/card")
@RequiredArgsConstructor
public class CardController {


    private static final String V_1 = "application/vnd.jdbcspringbootapp.v_1+json";

    private final CardService cardService;

    @PostMapping(value = "/create", produces = {V_1})
    public ResponseEntity<ResponseDto<CreateCardRespDto>> createCard(@RequestBody CreateCardReqDto createCardReqDto) {
        ResponseDto<CreateCardRespDto> answer = new ResponseDto<>();

        CreateCardRespDto createCardRespDto = cardService.createCard(createCardReqDto);

        answer.setData(createCardRespDto);
        answer.setStatus(STATUS.OK);
        return ResponseEntity.ok(answer);
    }

    @GetMapping(value = "/getFirst")
    public String getFirstCard(){
        return null; //cardRepository.getCardEntityById(0L).toString();
    }
}
