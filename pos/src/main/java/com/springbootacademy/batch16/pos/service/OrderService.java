package com.springbootacademy.batch16.pos.service;

import com.springbootacademy.batch16.pos.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.batch16.pos.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size);
}
