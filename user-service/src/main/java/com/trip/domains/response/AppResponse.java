package com.trip.domains.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AppResponse<T> {
    private Integer code;
    private String message;
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T error;
}
