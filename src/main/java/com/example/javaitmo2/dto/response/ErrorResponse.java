package com.example.javaitmo2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements ResponseInterface {
    private String message;
    private int code = 0;
    private String trace;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
