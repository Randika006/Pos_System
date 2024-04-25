package com.springbootacademy.batch16.pos.util.mappers;

import com.springbootacademy.batch16.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch16.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.batch16.pos.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemGetResponseDTO> entityListToDtoList(List<Item> items);
    ItemGetResponseDTO entityToDto(Item item);
    Item dtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemGetResponseDTO>LisDTOToPage(Page<Item> items);
}
