package com.springbootacademy.batch16.pos.service.impl;

import com.google.common.reflect.TypeToken;
import com.springbootacademy.batch16.pos.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.batch16.pos.dto.queryinterfaces.OrderDetailInterface;
import com.springbootacademy.batch16.pos.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.batch16.pos.dto.response.ResponseOrderDetailsDTO;
import com.springbootacademy.batch16.pos.entity.Order;
import com.springbootacademy.batch16.pos.entity.OrderDetails;
import com.springbootacademy.batch16.pos.repo.CustomerRepo;
import com.springbootacademy.batch16.pos.repo.ItemRepo;
import com.springbootacademy.batch16.pos.repo.OrderDetailRepo;
import com.springbootacademy.batch16.pos.repo.OrderRepo;
import com.springbootacademy.batch16.pos.service.OrderService;
import com.springbootacademy.batch16.pos.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceIMPL<T> implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order= new Order(
                customerRepo.getReferenceById(requestOrderSaveDTO.getCustomers()),
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal()
        );
        orderRepo.save(order);

        if(orderRepo.existsById(order.getOrderId())){
            //List<OrderDetails>orderDetails= new ArrayList<>();


            List<OrderDetails>orderDetails= modelMapper.
                    map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>(){
            }.getType());

            for(int i=0;i<orderDetails.size();i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getReferenceById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));
            }



            if(orderDetails.size()>0){
               orderDetailRepo.saveAll(orderDetails);
            }
            return "saved";

        }
        return null;
    }

    @Override
    public PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size) {
        List<OrderDetailInterface> orderDetailsDTOS=orderRepo.getAllOrderDetails(status, PageRequest.of(page, size));
        List<ResponseOrderDetailsDTO> list = new ArrayList<>();

        for (OrderDetailInterface o : orderDetailsDTOS) {
            ResponseOrderDetailsDTO r = new ResponseOrderDetailsDTO(
                    o.getCustomerName(),
                    o.getCustomerAddress(),
                    o.getContactNumber(),
                    o.getDate(),
                    o.getTotal()
            );
            list.add(r);
        }
        PaginatedResponseOrderDetails paginatedResponseOrderDetails = new PaginatedResponseOrderDetails(
                list,
                orderRepo.countAllOrderDetails(status)

        );
        return paginatedResponseOrderDetails;
    }
}
