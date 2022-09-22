package org.san.home.accounts.service.error;

import org.san.home.commons.error.RestExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice("org.san.home.accounts.controller")
public class AccountsRestExceptionHandler extends RestExceptionHandler {
}
