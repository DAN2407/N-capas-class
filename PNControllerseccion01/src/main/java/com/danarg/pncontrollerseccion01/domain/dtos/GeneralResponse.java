package com.danarg.pncontrollerseccion01.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeneralResponse {
    private String message;
    private Object data;

    public static ResponseEntity<GeneralResponse> getResponse(HttpStatus status, String message, Object data){
        return new ResponseEntity<>(
                new GeneralResponse(message, data),
                status
        );
    }

    public static ResponseEntity<GeneralResponse> getResponse(HttpStatus httpStatus) {
        return getResponse(httpStatus, httpStatus.getReasonPhrase(), null);
    }

    public static ResponseEntity<GeneralResponse> getResponse(HttpStatus httpStatus, String message) {
        return getResponse(httpStatus, message, null);
    }

    public static ResponseEntity<GeneralResponse> getResponse(HttpStatus httpStatus, Object data) {
        return getResponse(httpStatus, httpStatus.getReasonPhrase(), data);
    }



}
