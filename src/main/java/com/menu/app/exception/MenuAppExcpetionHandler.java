package com.menu.app.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.menu.app.ui.model.response.MenuAppError;

@ControllerAdvice
public class MenuAppExcpetionHandler {

	@ExceptionHandler(value = { MenuAppException.class })
	public ResponseEntity<MenuAppError> handleAnyException(MenuAppException mnuExp, WebRequest webReq) {

		MenuAppError mnuError = new MenuAppError(new Date(), mnuExp.getMessage());

		return new ResponseEntity<MenuAppError>(mnuError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
