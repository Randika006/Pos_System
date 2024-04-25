package com.springbootacademy.batch16.pos.service;

import com.springbootacademy.batch16.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.batch16.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.batch16.pos.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);

    List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String itemName);

    List<ItemGetResponseDTO> getItemsByActiveStatus(boolean activeStatus);

    PaginatedResponseItemDTO getItemsByActiveStatusWithPaginated(boolean activeStatus, int page, int size);

    PaginatedResponseItemDTO getAllActiveItemsPaginated(int page, int size, boolean activeStatus);
}
