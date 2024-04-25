package com.springbootacademy.batch16.pos.dto.response;


import com.springbootacademy.batch16.pos.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemGetResponseDTO {

    private int itemId;

    private String itemName;

    private double balanceQty;

    private double supplierPrice;

    private double sellingPrice;
    private boolean activeState;

}
