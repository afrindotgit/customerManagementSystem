package com.customermanagementsystem.repository;

import com.customermanagementsystem.dto.RequestDTO;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface CustomerDao {

    @Results(id = "customerQueryResults", value = {
            @Result(property = "customerName", column = "CUSTOMER_NAME"),
            @Result(property = "mobileNumber", column = "MOBILE_NUMBER"),
            @Result(property = "emailId", column = "EMAIL_ID")
    })
    @Insert(
        "INSERT INTO CUSTOMER (CUSTOMER_NAME,MOBILE_NUMBER,EMAIL_ID) VALUES ( #{customerName}, "
            +"#{mobileNumber},#{emailId})")
    Integer insertCustomer(final RequestDTO requestDTO);

    @Delete("DELETE FROM CUSTOMER WHERE CUSTOMER_ID in(SELECT MAX(CUSTOMER_ID) FROM CUSTOMER)")
    Integer deleteCustomer();
}
