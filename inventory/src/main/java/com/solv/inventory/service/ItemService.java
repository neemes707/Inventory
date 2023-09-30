package com.solv.inventory.service;

import com.solv.inventory.dto.ItemDto;
import com.solv.inventory.dto.ItemResponse;
import com.solv.inventory.exceptions.ItemNotFoundException;
import org.springframework.http.ResponseEntity;

public interface ItemService {
    ResponseEntity<ItemResponse> add(ItemDto itemDto);

    ResponseEntity<ItemResponse> getAllItems(int pageNumber, int pageSize);

    ResponseEntity<ItemResponse> searchItems(String value, int pageNumber, int pageSize);

    ResponseEntity<ItemResponse> getItemsInOrder(int pageNumber, int pageSize, String sortBy, String order);

    ResponseEntity<ItemResponse> getItemsInPriceRange(double minPrice, double maxPrice) ;
    ResponseEntity<ItemResponse> getItemsOfCategory(String category, int pageNumber, int pageSize);

    ResponseEntity<ItemResponse> getItemsOfCategoryInPriceRange(String category, double minPrice, double maxPrice);

    ResponseEntity<ItemResponse> getItemsThatMatchesQuery(String query);

    ResponseEntity<ItemResponse> searchDefault(String category,double minPrice,double maxPrice);

}
