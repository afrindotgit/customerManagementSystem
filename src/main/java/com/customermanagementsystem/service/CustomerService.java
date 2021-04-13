package com.customermanagementsystem.service;

import com.customermanagementsystem.dto.RequestDTO;
import com.customermanagementsystem.dto.ResponseDTO;
import com.customermanagementsystem.exception.CustomerException;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    ResponseEntity<ResponseDTO> push(RequestDTO requestDTO, String defaultDataSource) throws CustomerException;

    ResponseEntity<Object> pop(String defaultDataSource) throws CustomerException;
}
