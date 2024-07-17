package com.example.ShareFit.common.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto {
    private Object data;
    private String message;
    private String path;
    private String timestamp;
    private int statusCode;
}
