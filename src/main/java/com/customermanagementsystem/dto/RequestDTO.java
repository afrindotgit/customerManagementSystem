package com.customermanagementsystem.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestDTO {

    private String customerName;
    private String mobileNumber;
    private String emailId;
}
