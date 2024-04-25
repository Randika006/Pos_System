package com.springbootacademy.batch16.pos.service.impl;

import com.springbootacademy.batch16.pos.config.ModelMapperConfig;
import com.springbootacademy.batch16.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.batch16.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch16.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.batch16.pos.entity.Customer;
import com.springbootacademy.batch16.pos.entity.Item;
import com.springbootacademy.batch16.pos.entity.enums.MeasuringUnitType;
import com.springbootacademy.batch16.pos.exception.NotFoundException;
import com.springbootacademy.batch16.pos.repo.ItemRepo;
import com.springbootacademy.batch16.pos.service.ItemService;
import com.springbootacademy.batch16.pos.util.mappers.ItemMapper;
import org.hibernate.mapping.Map;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {

       // Item item=modelMapper.map(itemSaveRequestDTO,Item.class);
       Item item =itemMapper.dtoToEntity(itemSaveRequestDTO);
       if(!itemRepo.existsById(item.getItemId())){
           itemRepo.save(item);
           return item.getItemId()+ "saved sucessfull";
       }else {
           throw new DuplicateKeyException("Already added");
       }

    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
        boolean b =true;
        List<Item> items =itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName,b);
        if(items.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTOS= modelMapper.map(items,new TypeToken<List<ItemGetResponseDTO>>(){}.getType());

            return  itemGetResponseDTOS;
        }else{
            throw new RuntimeException("Item is not active");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String itemName) {
        boolean b =true;
        List<Item> items =itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName,b);
        if(items.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTOS=itemMapper.entityListToDtoList(items);

            return  itemGetResponseDTOS;
        }else{
            throw new RuntimeException("Item is not active");
        }

    }

    @Override
    public List<ItemGetResponseDTO> getItemsByActiveStatus(boolean activeStatus) {
        List<Item> items =itemRepo.findAllByActiveStateEquals(activeStatus);
        if(items.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTOS=itemMapper.entityListToDtoList(items);

            return  itemGetResponseDTOS;
        }else{
            throw new NotFoundException("Item is not active");
        }

    }

    @Override
    public PaginatedResponseItemDTO getItemsByActiveStatusWithPaginated(boolean activeStatus, int page, int size) {
        Page<Item> items = itemRepo.findAllByActiveStateEquals(activeStatus, PageRequest.of(page, size));
        if(items.getSize()<1){
            throw new NotFoundException("No Data");
        }
        PaginatedResponseItemDTO paginatedResponseItemDTO =new PaginatedResponseItemDTO(
                itemMapper.LisDTOToPage(items),
                itemRepo.countAllByActiveStateEquals(activeStatus)
        );
        return  paginatedResponseItemDTO;

    }

    @Override
    public PaginatedResponseItemDTO getAllActiveItemsPaginated(int page, int size, boolean activeState) {
        Page<Item> getAllActiveItemsByPaginated = itemRepo.findAllByActiveStateEquals(activeState,PageRequest.of(page, size));
        return new PaginatedResponseItemDTO(
                itemMapper.LisDTOToPage(getAllActiveItemsByPaginated),
                itemRepo.countAllByActiveStateEquals(activeState)
        );

    }


}
