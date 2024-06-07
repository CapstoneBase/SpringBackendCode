package com.capstone.yeolmaeTeamProject.global.handler;

import com.capstone.yeolmaeTeamProject.domain.user.exception.AlreadyExistAccountException;
import com.capstone.yeolmaeTeamProject.global.auth.exception.AuthenticationInfoNotFoundException;
import com.capstone.yeolmaeTeamProject.global.auth.exception.TokenValidateException;
import com.capstone.yeolmaeTeamProject.global.common.dto.ApiResponse;
import com.capstone.yeolmaeTeamProject.global.common.file.exception.FileUploadFailException;
import com.capstone.yeolmaeTeamProject.global.exception.NotFoundException;
import com.capstone.yeolmaeTeamProject.global.exception.PermissionDeniedException;

import com.google.gson.stream.MalformedJsonException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.transaction.TransactionSystemException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletionException;

@RestControllerAdvice(basePackages = "com.capstone.yeolmaeTeamProject")
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({
            AlreadyExistAccountException.class,
            StringIndexOutOfBoundsException.class,
            MissingServletRequestParameterException.class,
            MalformedJsonException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            IllegalAccessException.class,
    })
    public ApiResponse<Object> badRequestException(HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_OK);
        return ApiResponse.failure();
    }

    @ExceptionHandler({
            AuthenticationInfoNotFoundException.class,
            AccessDeniedException.class,
            BadCredentialsException.class,
            TokenValidateException.class,
    })
    public ApiResponse<Object> unAuthorizeException(HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return ApiResponse.failure();
    }

    @ExceptionHandler({
            PermissionDeniedException.class,
    })
    public ApiResponse<Object> deniedException(HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return ApiResponse.failure();
    }

    @ExceptionHandler({
            NullPointerException.class,
            NotFoundException.class,
            NoSuchElementException.class,
            FileNotFoundException.class,
    })
    public ApiResponse<Object> notFoundException(HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_OK);
        return ApiResponse.failure();
    }

    @ExceptionHandler({
            IllegalStateException.class,
            FileUploadFailException.class,
            DataIntegrityViolationException.class,
            IncorrectResultSizeDataAccessException.class,
            ArrayIndexOutOfBoundsException.class,
            IOException.class,
            TransactionSystemException.class,
            SecurityException.class,
            CompletionException.class,
            Exception.class
    })
    public ApiResponse<Object> serverException(HttpServletResponse response, Exception e) {
        log.warn(e.getMessage());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return ApiResponse.failure();
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class
    })
    public ApiResponse<Object> handleValidationException(HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return ApiResponse.failure();
    }
}
