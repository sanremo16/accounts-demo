package org.san.home.commons.error;

/**
 * @author sanremo16
 */
public enum CommonErrorCode implements ErrorCode {
    UNDEFINED(0, "undefined"),
    VALIDATION_ERROR(14, "validation_error");

    private Integer code;
    private String name;

    CommonErrorCode(Integer errCode, String errName) {
        code = errCode;
        name = errName;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
