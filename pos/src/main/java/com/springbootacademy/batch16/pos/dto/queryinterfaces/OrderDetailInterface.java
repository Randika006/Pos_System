package com.springbootacademy.batch16.pos.dto.queryinterfaces;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailInterface {

    String getCustomerName();

    String getCustomerAddress();

    ArrayList getContactNumber();

    Date getDate();

    Double getTotal();





}
