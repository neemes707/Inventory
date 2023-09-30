package com.solv.inventory.controller;

import com.solv.inventory.dto.ItemDto;
import com.solv.inventory.dto.ItemResponse;
import com.solv.inventory.exceptions.ItemNotFoundException;
import com.solv.inventory.service.impl.ItemServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Services for items")
@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {
    @Autowired
    ItemServiceImpl itemserviceimpl;
    @ApiOperation("-adds an item")
    @PostMapping("/")
    public ResponseEntity<ItemResponse> addItem(ItemDto itemDto) {
        return this.itemserviceimpl.add(itemDto);
    }

    @ApiOperation("gives all items")
    @GetMapping("/items")
    public ResponseEntity<ItemResponse> getItems(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                 @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        return this.itemserviceimpl.getAllItems(pageNumber, pageSize);
    }

    @ApiOperation("-return list of items having name that contains title")
    @GetMapping("/items/name")
    public ResponseEntity<ItemResponse> searchItem(@RequestParam(value = "title",defaultValue = "",required = false) String title,
                                                   @RequestParam(name = "pageNumber",defaultValue = "0",required = false)int pageNumber,
                                                   @RequestParam(name = "pageSize",defaultValue = "5",required = false)int pageSize){
        return this.itemserviceimpl.searchItems(title,pageNumber,pageSize);
    }

    @ApiOperation("-returns the list of items in a sorted order based on given category")
    @GetMapping("/items/order")
    public ResponseEntity<ItemResponse> getItems(@RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                 @RequestParam(name = "pageSize",defaultValue = "2",required = false)int pageSize,
                                                 @RequestParam(name = "sortBy",defaultValue = "id",required = false)String sortBy,
                                                 @RequestParam(name = "order",defaultValue = "asc",required = false)String order) {
         return this.itemserviceimpl.getItemsInOrder(pageNumber,pageSize,sortBy,order);
    }
    @ApiOperation("-returns all items which lies in a particular price range")
    @GetMapping("/items/price")
    public ResponseEntity<ItemResponse> getItemBasedOnPrice(@RequestParam(name = "minPrice",defaultValue = "0.0",required = false)double minPrice,
                                                            @RequestParam(name = "maxPrice",defaultValue = "100000000000.0",required = false)double maxPrice) throws ItemNotFoundException {
        return this.itemserviceimpl.getItemsInPriceRange(minPrice,maxPrice);
    }
    @ApiOperation("-return all items which belongs to a given category")
    @GetMapping("items/category")
    public ResponseEntity<ItemResponse> getItemsBasedOnCategory(@RequestParam(value = "category",defaultValue = "",required = false)String category,
                                                                @RequestParam(value = "pageNumber",defaultValue = "0",required = false)int pageNumber,
                                                                @RequestParam(value = "pageSize",defaultValue = "5",required = false)int pageSize) throws ItemNotFoundException {
        return this.itemserviceimpl.getItemsOfCategory(category,pageNumber,pageSize);
    }
    @ApiOperation("-return all items based of particular category which lies in the given price range")
    @GetMapping("items/filter")
    public ResponseEntity<ItemResponse> getItemBasedOnCategoryAndPrice(@RequestParam(value = "category",defaultValue = "",required = false)String category,
                                                                       @RequestParam(name = "minPrice",defaultValue = "0",required = false)double minPrice,
                                                                       @RequestParam(name = "maxPrice",defaultValue = "1000000000000",required = false)double maxPrice) {
        return this.itemserviceimpl.getItemsOfCategoryInPriceRange(category,minPrice,maxPrice);
    }
    @ApiOperation("-return the list of items that matches the given query")
    @GetMapping("items/find")
    public ResponseEntity<ItemResponse> getItemsThatMatchesQuery(@RequestParam(value = "query",required = true,defaultValue = "") String query){
        return this.itemserviceimpl.getItemsThatMatchesQuery(query);
    }
}

