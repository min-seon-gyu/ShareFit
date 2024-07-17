package com.example.ShareFit.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice(
        basePackages = {"com.example.ShareFit.security.auth.controller", "com.example.ShareFit.domain"}
)
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {


/*        if (body instanceof ErrorData errorData) {
            ExceptionRule exceptionRule = errorData.getExceptionRule();
            response.setStatusCode(exceptionRule.getStatus());

            return ApiResponse.builder()
                    .path(path)
                    .data(errorData.getRejectedValues())
                    .message(exceptionRule.getMessage())
                    .build();
        }*/

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setData(body);
        apiResponseDto.setPath(request.getURI().toString());
        apiResponseDto.setMessage("Success");
        apiResponseDto.setStatusCode(200);
        apiResponseDto.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        if (body instanceof String) {
            try {
                return new ObjectMapper().writeValueAsString(apiResponseDto);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error processing JSON", e);
            }
        }

        return apiResponseDto;
    }
}