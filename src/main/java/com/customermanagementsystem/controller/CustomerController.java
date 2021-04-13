package com.customermanagementsystem.controller;

import com.customermanagementsystem.dto.RequestDTO;
import com.customermanagementsystem.dto.ResponseDTO;
import com.customermanagementsystem.exception.CustomerException;
import com.customermanagementsystem.service.CustomerService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
@Api(value = "CustomerController", description = "REST APIs related to Customer Management")
public class CustomerController {

    private final CustomerService customerService;

    @Value("${application.default.datasource}")
    private String defaultDataSource;

    @ApiOperation(value = "Insert Customer into Selected Data Source", response = ResponseEntity.class, tags = "push")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found")})

    @PostMapping("/push")
    public ResponseEntity<ResponseDTO> push(@RequestBody RequestDTO requestDTO, @RequestParam @ApiParam(
            allowableValues = "datasource_a,datasource_b") Optional<String> dataSource) throws CustomerException {

        dataSource.ifPresent(source -> defaultDataSource = source);
        return customerService.push(requestDTO, defaultDataSource);

    }

    @ApiOperation(value = "Delete Last Inserted Customer From Selected Data Source", response = ResponseEntity.class, tags = "pop")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found")})

    @DeleteMapping("/pop")
    public ResponseEntity<Object> pop(@RequestParam @ApiParam(allowableValues = "datasource_a,datasource_b")
                                                  Optional<String> dataSource) throws CustomerException {

        dataSource.ifPresent(source -> defaultDataSource = source);
        return customerService.pop(defaultDataSource);

    }

}
