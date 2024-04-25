package com.springbootacademy.batch16.pos.controller;

import com.springbootacademy.batch16.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.batch16.pos.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.batch16.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch16.pos.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.batch16.pos.service.OrderService;
import com.springbootacademy.batch16.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin

public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping(
            path = {"/save"}
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO){
        String id =  orderService.addOrder(requestOrderSaveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,id + "item sucessfully saves",id),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            params = {"stateType","page","size"},
            path = {"/get-order-details"}
    )
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value="stateType")String stateType,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(value=50)int size

    ){
        PaginatedResponseOrderDetails p = null;
        if(stateType.equalsIgnoreCase("active") | stateType.equalsIgnoreCase("inactive")){
              boolean status=stateType.equalsIgnoreCase("active") ? true : false;
              p=orderService.getAllOrderDetails(status,page,size);
        }

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"SUCCESS",p),
                HttpStatus.OK
        );
    }

}
