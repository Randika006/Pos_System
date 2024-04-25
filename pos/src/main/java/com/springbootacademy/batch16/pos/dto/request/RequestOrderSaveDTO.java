package com.springbootacademy.batch16.pos.dto.request;

import com.springbootacademy.batch16.pos.entity.Customer;
import com.springbootacademy.batch16.pos.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RequestOrderSaveDTO {
    private int customers;
    private Date date;
    private Double total;
    private List<RequestOrderDetailsSave> orderDetails;

}
