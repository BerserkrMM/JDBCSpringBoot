package com.example.jdbcspringbootapp.service.currency;

import com.example.jdbcspringbootapp.model.dto.request.currency.CreateCurrencyReqDto;
import com.example.jdbcspringbootapp.model.dto.response.ErrorDto;
import com.example.jdbcspringbootapp.model.dto.response.ResponseDto;
import com.example.jdbcspringbootapp.model.dto.response.currency.CreateCurrencyRespDto;
import com.example.jdbcspringbootapp.model.enums.Status;
import com.example.jdbcspringbootapp.repository.currency.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

//@ActiveProfiles("test")
//@SpringBootTest(classes = CurrencyServiceImpl.class)
class CurrencyServiceImplTest {
//
//    @Autowired
//    CurrencyService currencyService;
//
//    @MockBean
//    CurrencyRepository currencyRepository;
//
//    @Test
//    void createCurrency_thenThrowEx() {
//        //given
//        var reqDto = CreateCurrencyReqDto.builder()
//                .code("code")
//                .name("name")
//                .exchange_rate_to_usd(BigDecimal.ONE)
//                .build();
//        var respDto = CreateCurrencyRespDto.builder()
//                .code("code")
//                .name("name")
//                .exchange_rate_to_usd(BigDecimal.ONE)
//                .build();
//
//        when(currencyRepository.isPresentByCode(reqDto.getCode(),CreateCurrencyRespDto.class))
//                .thenReturn(Optional.of(respDto));
//
//        //when
//        assertThatThrownBy(() -> currencyService.createCurrency(reqDto))
//                .isInstanceOf(IllegalAccessException.class);
//
//        //then
//        verify(currencyRepository).isPresentByCode(reqDto.getCode(), CreateCurrencyRespDto.class);
//        verify(currencyRepository, never()).createCurrency(any());
//    }
//
//    @Test
//    void createCurrency_ok() throws IllegalAccessException {
//        //given
//        var code = "code";
//        var name = "name";
//        var reqDto = CreateCurrencyReqDto.builder()
//                .code(code)
//                .name(name)
//                .build();
//
//        when(currencyRepository.tryExistenceByCode(reqDto.getCode()))
//                .thenReturn(Optional.empty());
//
//        var createdDto = CreateCurrencyRespDto.builder()
//                .code(code)
//                .name(name)
//                .exchange_rate_to_usd(BigDecimal.ONE)
//                .build();
//        when(currencyRepository.createCurrency(reqDto)).thenReturn(Optional.of(createdDto));
//
//        ResponseDto<CreateCurrencyRespDto> expectedResult = new ResponseDto<>();
//        expectedResult.setStatus(Status.OK);
//        expectedResult.setData(createdDto);
//
//        //when
//        var actualResult = currencyService.createCurrency(reqDto);
//        assertThat(actualResult)
//                .isNotNull()
//                .isEqualTo(expectedResult);
//
//        //then
//        verify(currencyRepository).tryExistenceByCode(reqDto.getCode());
//        verify(currencyRepository).createCurrency(reqDto);
//    }
//
//    @Test
//    void createCurrency_notCreated() throws IllegalAccessException {
//        //given
//        var code = "code";
//        var name = "name";
//        var reqDto = CreateCurrencyReqDto.builder()
//                .code(code)
//                .name(name)
//                .build();
//
//        when(currencyRepository.tryExistenceByCode(reqDto.getCode()))
//                .thenReturn(Optional.empty());
//        when(currencyRepository.createCurrency(reqDto))
//                .thenReturn(Optional.empty());
//
//        ResponseDto<CreateCurrencyRespDto> expectedAnswer = new ResponseDto<>();
//        expectedAnswer.setData(null);
//        expectedAnswer.setStatus(Status.FAILED);
//        expectedAnswer.setErrors(List.of(new ErrorDto("Currency is not created.")));
//
//        //when
//        var answer = currencyService.createCurrency(reqDto);
//        assertThat(answer)
//                .isNotNull()
//                .isEqualTo(expectedAnswer);
//        //then
//        verify(currencyRepository).tryExistenceByCode(reqDto.getCode());
//        verify(currencyRepository).createCurrency(reqDto);
//    }
}