package com.example.jdbcspringbootapp.model.dto.response;

import com.example.jdbcspringbootapp.model.enums.STATUS;

import java.util.List;

//@ApiModel("DBaseResponse")
public class BaseResponseDto {
    //    @ApiModelProperty("Response status")
    private STATUS status;
    //    @ApiModelProperty("List of errors encountered during the request")
    private List<ErrorDto> errors;

    public STATUS getStatus() {
        return this.status;
    }

    public List<ErrorDto> getErrors() {
        return this.errors;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public void setErrors(List<ErrorDto> errors) {
        this.errors = errors;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseResponseDto)) {
            return false;
        } else {
            BaseResponseDto other = (BaseResponseDto) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                Object this$errors = this.getErrors();
                Object other$errors = other.getErrors();
                if (this$errors == null) {
                    if (other$errors != null) {
                        return false;
                    }
                } else if (!this$errors.equals(other$errors)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseResponseDto;
    }

    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $errors = this.getErrors();
        result = result * 59 + ($errors == null ? 43 : $errors.hashCode());
        return result;
    }

    public String toString() {
        STATUS var10000 = this.getStatus();
        return "BaseResponseDto(status=" + var10000 + ", errors=" + this.getErrors() + ")";
    }

    public BaseResponseDto() {
    }

    public BaseResponseDto(STATUS status, List<ErrorDto> errors) {
        this.status = status;
        this.errors = errors;
    }

    public BaseResponseDto withStatus(STATUS status) {
        return this.status == status ? this : new BaseResponseDto(status, this.errors);
    }

    public BaseResponseDto withErrors(List<ErrorDto> errors) {
        return this.errors == errors ? this : new BaseResponseDto(this.status, errors);
    }
}
