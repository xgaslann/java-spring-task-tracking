package com.xgaslan.tasks.controllers;

import com.xgaslan.tasks.domain.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public interface IGlobalExceptionHandler {

    ResponseEntity<ErrorResponse> handleException(RuntimeException ex, WebRequest request);
}
