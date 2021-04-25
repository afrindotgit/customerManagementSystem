package com.customermanagementsystem.controller;

import com.customermanagementsystem.dto.RequestDTO;
import com.customermanagementsystem.dto.ResponseDTO;
import com.customermanagementsystem.exception.CustomerException;
import com.customermanagementsystem.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController underTest;

    private String defaultDataSource;
    private RequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        defaultDataSource = "datasource_a";
        requestDTO = RequestDTO.builder().customerName("Test").mobileNumber("08809989878").build();
    }

    @Test
    void givenValidRequestDTO_whenSaveCustomer_thenReturnSuccess() throws CustomerException {

        //GIVEN
        when(customerService.push(requestDTO, defaultDataSource)).thenReturn(new ResponseEntity<>(
                ResponseDTO.builder()
                        .message("Customer Inserted Successfully")
                        .statusCode("201").build(), HttpStatus.CREATED));

        //WHEN
        ResponseEntity<ResponseDTO> response = underTest.push(requestDTO, Optional.of(defaultDataSource));

        //THEN
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    void givenDataSource_whenDeleteCustomer_thenReturnSuccess() throws CustomerException {

        //GIVEN
        when(customerService.pop(defaultDataSource)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        //WHEN
        ResponseEntity<Object> response = underTest.pop(Optional.of(defaultDataSource));

        //THEN
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);

    }
}
