package com.example.jdbcspringbootapp.model.dto.response;

import com.example.jdbcspringbootapp.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseDto {
    //    @ApiModelProperty("Response status")
    private Status status;
    //    @ApiModelProperty("List of errors encountered during the request")
    private List<ErrorDto> errors;

    public static BaseResponseDto createSingleFieldDto(String message){
        BaseResponseDto responseDto = new BaseResponseDto();
        responseDto.setStatus(Status.FAILED);
        responseDto.setErrors(List.of(new ErrorDto(message)));
        return new BaseResponseDto();
    }
}
