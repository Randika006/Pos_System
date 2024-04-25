package com.springbootacademy.batch16.pos.dto.request;

import com.springbootacademy.batch16.pos.entity.Item;
import com.springbootacademy.batch16.pos.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RequestOrderDetailsSave {

    private String itemName;
    private double qty;
    private Double amount;
    private int items;

}
