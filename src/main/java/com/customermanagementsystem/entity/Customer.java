package com.customermanagementsystem.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    @NonNull
    @ApiModelProperty(notes = "Customer Name",name="customerName",required=true,value="Marcus")
    private String customerName;
    @NonNull
    @ApiModelProperty(notes = "Mobile Number",name="mobileNumber",required=true,value="09828982839")
    private String mobileNumber;
    @ApiModelProperty(notes = "Country",name="country",value="India")
    private String country;
}


