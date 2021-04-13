package com.customermanagementsystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseDTO {

    private String message;
    private String statusCode;
}
