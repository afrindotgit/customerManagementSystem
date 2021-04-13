package com.customermanagementsystem.controller;

import com.customermanagementsystem.dto.RequestDTO;
import com.customermanagementsystem.dto.ResponseDTO;
import com.customermanagementsystem.exception.CustomerException;
import com.customermanagementsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Value("${application.default.datasource}")
    private String defaultDataSource;

    @PostMapping("/push")
    public ResponseEntity<ResponseDTO> push(@RequestBody RequestDTO requestDTO, @RequestParam Optional<String> dataSource) throws CustomerException {

        dataSource.ifPresent(source -> defaultDataSource = source);
        return customerService.push(requestDTO, defaultDataSource);

    }

    @DeleteMapping("/pop")
    public ResponseEntity<Object> pop(@RequestParam Optional<String> dataSource) throws CustomerException {

        dataSource.ifPresent(source -> defaultDataSource = source);
        return customerService.pop(defaultDataSource);

    }

}
