package com.springbootacademy.batch16.pos.dto.request;

import com.springbootacademy.batch16.pos.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemSaveRequestDTO {

    private String itemName;

    private MeasuringUnitType measuringUnitType;

    private double balanceQty;

    private double supplierPrice;

    private double sellingPrice;

}
