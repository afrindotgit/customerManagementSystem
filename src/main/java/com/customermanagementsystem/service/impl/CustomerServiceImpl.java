package com.customermanagementsystem.service.impl;

import com.customermanagementsystem.config.DataSourceContextHolder;
import com.customermanagementsystem.dto.RequestDTO;
import com.customermanagementsystem.dto.ResponseDTO;
import com.customermanagementsystem.exception.CustomerException;
import com.customermanagementsystem.repository.CustomerDao;
import com.customermanagementsystem.service.CustomerService;
import com.customermanagementsystem.utils.DataSourceEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Override
    public ResponseEntity<ResponseDTO> push(final RequestDTO requestDTO, String defaultDataSource) throws CustomerException {

        if (!Pattern.matches("(0/91)?[0-9]{10}", requestDTO.getMobileNumber())) {
            throw new CustomerException("Invalid Mobile Number");
        }
        return Optional.of(DataSourceEnum.valueOf(defaultDataSource)).map(response -> {
            DataSourceContextHolder.setCurrentDb(response);
            customerDao.insertCustomer(requestDTO);
            return new ResponseEntity<>(ResponseDTO.builder()
                    .message("Customer Inserted Successfully")
                    .statusCode("201").build(), HttpStatus.CREATED);
        })
                .orElseThrow(() -> new CustomerException("Customer Insertion Failed"));
    }

    @Override
    public ResponseEntity<Object> pop(String defaultDataSource) throws CustomerException {

        return Optional.ofNullable(customerDao.deleteCustomer())
                .map(response -> new ResponseEntity<>(HttpStatus.NO_CONTENT))
                .orElseThrow(() -> new CustomerException("Customer Deletion Failed"));

    }
}


