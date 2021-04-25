package com.customermanagementsystem.service.impl;

import com.customermanagementsystem.dto.RequestDTO;
import com.customermanagementsystem.dto.ResponseDTO;
import com.customermanagementsystem.exception.CustomerException;
import com.customermanagementsystem.repository.CustomerDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerServiceImpl underTest;

    private String defaultDataSource;
    private RequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        defaultDataSource = "datasource_a";
        requestDTO = RequestDTO.builder().customerName("Test").mobileNumber("08809989878").build();
    }

    @Test
    void givenInvalidMobileNumber_whenSaveCustomer_thenReturnError() throws CustomerException {

        //GIVEN
        requestDTO = RequestDTO.builder().customerName("Test").mobileNumber("08808888").build();

        //THEN
        Exception exception = Assertions.assertThrows(CustomerException.class,
                () -> underTest.push(requestDTO, defaultDataSource));

        assertTrue(exception.getMessage().contains("Invalid Mobile Number"));
    }

    @Test
    void givenInvalidDataSource_whenSaveCustomer_thenReturnError() throws CustomerException {

        //THEN
        Exception exception = Assertions.assertThrows(CustomerException.class,
                () -> underTest.push(requestDTO, "datasource_c"));

        assertTrue(exception.getMessage().contains("Invalid Data Source"));
    }

    @Test
    void givenValidRequestDTO_whenSaveCustomer_thenReturnSuccess() throws CustomerException {

        //GIVEN
        when(customerDao.insertCustomer(requestDTO)).thenReturn(1);

        //WHEN
        ResponseEntity<ResponseDTO> response = underTest.push(requestDTO, defaultDataSource);

        //THEN
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    void givenInvalidDataSource_whenDeleteCustomer_thenReturnError() throws CustomerException {

        //THEN
        Exception exception = Assertions.assertThrows(CustomerException.class,
                () -> underTest.pop("datasource_c"));

        assertTrue(exception.getMessage().contains("Invalid Data Source"));
    }

    @Test
    void givenDataSource_whenDeleteCustomer_thenReturnSuccess() throws CustomerException {

        //GIVEN
        when(customerDao.deleteCustomer()).thenReturn(1);

        //WHEN
        ResponseEntity<Object> response = underTest.pop(defaultDataSource);

        //THEN
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);

    }
}
