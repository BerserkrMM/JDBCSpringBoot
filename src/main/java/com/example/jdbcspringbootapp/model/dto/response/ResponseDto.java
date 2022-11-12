package com.example.jdbcspringbootapp.model.dto.response;


public class ResponseDto<T> extends BaseResponseDto {
    //    @ApiModelProperty("Response data.")
    private T data;

    public ResponseDto() {
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResponseDto)) {
            return false;
        } else {
            ResponseDto<?> other = (ResponseDto) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResponseDto;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "ResponseDto(data=" + this.getData() + ")";
    }
}
