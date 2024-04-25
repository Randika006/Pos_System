package com.springbootacademy.batch16.pos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrderDetailsDTO {
   //customer
    private String customerName;
    private String customerAddress;
    private ArrayList contactNumber;

    //order
    private Date date;
    private Double total;
}
