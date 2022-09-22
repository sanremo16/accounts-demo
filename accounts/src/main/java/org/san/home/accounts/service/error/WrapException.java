package org.san.home.accounts.service.error;

import org.san.home.accounts.error.AccountsErrorCode;
import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WrapException {

    AccountsErrorCode errorCode() default AccountsErrorCode.UNDEFINED;
}
