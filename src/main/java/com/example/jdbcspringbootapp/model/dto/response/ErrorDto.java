package com.example.jdbcspringbootapp.model.dto.response;

public class ErrorDto {
    //    @ApiModelProperty("Code of the error.")
    private Integer code;
    //    @ApiModelProperty("Error message.")
    private String message;
    //    @ApiModelProperty("Source service.")
    private Integer source;

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getSource() {
        return this.source;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ErrorDto)) {
            return false;
        } else {
            ErrorDto other = (ErrorDto) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47:
                {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label47;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label47;
                    }

                    return false;
                }

                Object this$source = this.getSource();
                Object other$source = other.getSource();
                if (this$source == null) {
                    if (other$source != null) {
                        return false;
                    }
                } else if (!this$source.equals(other$source)) {
                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ErrorDto;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $source = this.getSource();
        result = result * 59 + ($source == null ? 43 : $source.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getCode();
        return "ErrorDto(code=" + var10000 + ", message=" + this.getMessage() + ", source=" + this.getSource() + ")";
    }

    public ErrorDto() {
    }

    public ErrorDto(Integer code, String message, Integer source) {
        this.code = code;
        this.message = message;
        this.source = source;
    }

    public ErrorDto withCode(Integer code) {
        return this.code == code ? this : new ErrorDto(code, this.message, this.source);
    }

    public ErrorDto withMessage(String message) {
        return this.message == message ? this : new ErrorDto(this.code, message, this.source);
    }

    public ErrorDto withSource(Integer source) {
        return this.source == source ? this : new ErrorDto(this.code, this.message, source);
    }
}
