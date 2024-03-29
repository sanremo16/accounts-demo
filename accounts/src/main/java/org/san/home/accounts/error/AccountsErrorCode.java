package org.san.home.accounts.error;

import org.san.home.commons.error.ErrorCode;

/**
 * @author sanremo16
 */
public enum AccountsErrorCode implements ErrorCode {
    INCOMPATIBLE_CURRENCY(1, "incompatible_currency"),
    INSUFFICIENT_MONEY(2, "insufficient_money"),
    TOPUP_FAILED(3, "topup_failed"),
    WITHDRAW_FAILED(4, "withdraw_failed"),
    TRANSFER_FAILED(5, "transfer_failed"),
    GET_ACCOUNT_FAILED(6, "get_account_failed"),
    GET_ALL_FAILED(7, "get_all_failed"),
    SAME_ACCOUNT_TRANSFER(8, "same_account_transfer"),
    TOO_MANY_ACCOUNTS(9, "too_many_accounts"),
    ADD_ACCOUNT_FAILED(10, "add_account_failed"),
    UPDATE_ACCOUNT_FAILED(11, "update_account_failed"),
    DELETE_ACCOUNT_FAILED(12, "delete_account_failed"),
    UNSUPPORTED_CURRENCY(13, "unsupported_currency"),
    UNDEFINED(0, "undefined"),
    VALIDATION_ERROR(14, "validation_error"),
    FIND_FAILED(15, "find_failed"),    ;

    private Integer code;
    private String name;

    AccountsErrorCode(Integer errCode, String errName) {
        this.code = errCode;
        this.name = errName;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
