package com.solv.inventory.service.impl;

import com.solv.inventory.dao.ItemRepository;
import com.solv.inventory.dto.ItemResponse;
import com.solv.inventory.entity.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static com.solv.inventory.mapper.ItemMapper.toItemDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ItemServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ItemServiceImplTest {
    @MockBean
    ItemRepository itemRepository;
    @Autowired
    ItemServiceImpl itemServiceImpl;
    @Test
    void testAddItemOk() {
        Item item=Item.builder()
                .name("Laptop")
                .category("Electronics")
                .price(100000)
                .build();
        when(itemRepository.save(item)).thenReturn(item);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.add(toItemDto(item));
        assertEquals("201 CREATED",itemResponseResponseEntity.getStatusCode().toString());
    }
    @Test
    void testAddItemNotOk(){
        Item item=Item.builder()
                .name("Laptop123")
                .category("Electronics")
                .price(100000)
                .build();
        when(itemRepository.save(item)).thenReturn(item);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.add(toItemDto(item));
        assertEquals("400 BAD_REQUEST",itemResponseResponseEntity.getStatusCode().toString());
        assertEquals("Enter the fields correctly", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusMessage());
    }
    @Test
    void testGetAllItemsOk() {
        Item item=Item.builder()
                .name("Laptop")
                .category("Electronics")
                .price(100000)
                .build();
        Item item1=Item.builder()
                .name("Chair")
                .category("Furniture")
                .price(1000)
                .build();
        List<Item> list=new ArrayList<>();
        list.add(item);
        list.add(item1);
        Pageable pageable=PageRequest.of(0,10);
        Page<Item> page=new PageImpl<>(list);
        when(itemRepository.findAll(pageable)).thenReturn(page);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.getAllItems(0,10);
        assertEquals("200 OK",itemResponseResponseEntity.getStatusCode().toString());
        assertEquals("Accepted", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusMessage());
    }
    @Test
    void testGetAllItemsNotOk() {
        List<Item> list=new ArrayList<>();
        Pageable pageable=PageRequest.of(0,10);
        Page<Item> page=new PageImpl<>(list);
        when(itemRepository.findAll(pageable)).thenReturn(page);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.getAllItems(0,10);
        assertEquals("400 BAD_REQUEST",itemResponseResponseEntity.getStatusCode().toString());
        assertEquals("Currently there are no items", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusMessage());
    }

    @Test
    void testSearchItemsOk() {
        Item item=Item.builder()
                .name("Laptop")
                .category("Electronics")
                .price(100000)
                .build();
        List<Item> list=new ArrayList<>();
        list.add(item);
        Pageable p= PageRequest.of(0,5);
        Page<Item> page=new PageImpl<>(list);
        when(itemRepository.findByNameContaining("Laptop",p)).thenReturn(page);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.searchItems("Laptop",0,5);
        assertEquals("200 OK", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusCode().toString());
        assertEquals("Accepted",itemResponseResponseEntity.getBody().getStatusMessage());
    }
    @Test
    void searchItemNotOk(){
        List<Item> list=new ArrayList<>();
        Pageable p= PageRequest.of(0,5);
        Page<Item> page=new PageImpl<>(list);
        when(itemRepository.findByNameContaining("Laptop",p)).thenReturn(page);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.searchItems("Laptop",0,5);
        assertEquals("400 BAD_REQUEST", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusCode().toString());
        assertEquals("Currently there are no items",itemResponseResponseEntity.getBody().getStatusMessage());
    }

    @Test
    void testGetItemsInOrderOk() {
        Item item=Item.builder()
                .name("Laptop")
                .category("Electronics")
                .price(100000)
                .build();
        Item item1=Item.builder()
                .name("Chair")
                .category("Furniture")
                .price(1000)
                .build();
        List<Item> list=new ArrayList<>();
        list.add(item1);
        list.add(item);
        Sort sort= Sort.by("id").ascending();
        PageRequest p=PageRequest.of(0,10,sort);
        Page<Item> page =new PageImpl<>(list);
        when(itemRepository.findAll(p)).thenReturn(page);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.getItemsInOrder(0,10,"id","asc");
        assertEquals("200 OK",itemResponseResponseEntity.getStatusCode().toString());
        assertEquals("Accepted", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusMessage());
    }
    @Test
    void testGetItemsInOrderNotOk() {
        List<Item> list=new ArrayList<>();
        Sort sort= Sort.by("id").ascending();
        PageRequest p=PageRequest.of(0,10,sort);
        Page<Item> page =new PageImpl<>(list);
        when(itemRepository.findAll(p)).thenReturn(page);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.getItemsInOrder(0,10,"id","asc");
        assertEquals("400 BAD_REQUEST",itemResponseResponseEntity.getStatusCode().toString());
        assertEquals("Currently there are no items", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusMessage());
        verify(itemRepository,times(1)).findAll(p);
    }

    @Test
    void testGetItemsInPriceRangeOk() {
        Item item=Item.builder()
                .name("Laptop")
                .category("Electronics")
                .price(10000)
                .build();
        Item item1=Item.builder()
                .name("Chair")
                .category("Furniture")
                .price(1000)
                .build();
        List<Item> list=new ArrayList<>();
        list.add(item);
        when(itemRepository.findByPriceBetween(1500,100000)).thenReturn(list);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.getItemsInPriceRange(1500,100000);
        assertEquals("200 OK",itemResponseResponseEntity.getStatusCode().toString());
        assertEquals("Accepted", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusMessage());
    }
    @Test
    void testGetItemsInPriceRangeNotOk() {
        List<Item> list=new ArrayList<>();
        when(itemRepository.findByPriceBetween(1500,100000)).thenReturn(list);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.getItemsInPriceRange(1500,100000);
        assertEquals("200 OK",itemResponseResponseEntity.getStatusCode().toString());
        assertEquals("Currently there are no items", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusMessage());
    }
    @Test
    void testGetItemsOfCategoryOk(){
        PageRequest p=PageRequest.of(0,10);
        Item item=Item.builder()
                .name("Laptop")
                .category("Electronics")
                .price(10000)
                .build();
        Item item1=Item.builder()
                .name("Chair")
                .category("Furniture")
                .price(1000)
                .build();
        List<Item> list=new ArrayList<>();
        list.add(item);
        Page<Item> page=new PageImpl<>(list);
        when(itemRepository.findByCategory("Electronics",p)).thenReturn(page);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.getItemsOfCategory("Electronics",0,10);
        assertEquals("200 OK",itemResponseResponseEntity.getStatusCode().toString());
        assertEquals("Accepted", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusMessage());
    }
    @Test
    void testGetItemsOfCategoryNotOk(){
        PageRequest p=PageRequest.of(0,10);
        Item item1=Item.builder()
                .name("Chair")
                .category("Furniture")
                .price(1000)
                .build();
        List<Item> list=new ArrayList<>();
        Page<Item> page=new PageImpl<>(list);
        when(itemRepository.findByCategory("Electronics",p)).thenReturn(page);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.getItemsOfCategory("Electronics",0,10);
        assertEquals("200 OK",itemResponseResponseEntity.getStatusCode().toString());
        assertEquals("Currently there are no items", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusMessage());
    }
    @Test
    void testGetItemsOfCategoryInPriceRangeOk() {
        Item item=Item.builder()
                .name("Laptop")
                .category("Electronics")
                .price(10000)
                .build();

        Item item1=Item.builder()
                .name("Chair")
                .category("Furniture")
                .price(1000)
                .build();
        List<Item> list=new ArrayList<>();
        list.add(item);
        when(itemRepository.findByCategoryAndPriceBetween("Electronics",2000,100000)).thenReturn(list);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.getItemsOfCategoryInPriceRange("Electronics",2000,100000);
        assertEquals("200 OK",itemResponseResponseEntity.getStatusCode().toString());
        assertEquals("Accepted", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusMessage());
        verify(itemRepository,times(1)).findByCategoryAndPriceBetween("Electronics",2000,100000);
    }
    @Test
    void testGetItemsOfCategoryInPriceRangeNotOk() {

        List<Item> list=new ArrayList<>();
        when(itemRepository.findByCategoryAndPriceBetween("Electronics",2000,100000)).thenReturn(list);
        ResponseEntity<ItemResponse> itemResponseResponseEntity=itemServiceImpl.getItemsOfCategoryInPriceRange("Electronics",2000,100000);
        assertEquals("200 OK",itemResponseResponseEntity.getStatusCode().toString());
        assertEquals("Currently there are no items", Objects.requireNonNull(itemResponseResponseEntity.getBody()).getStatusMessage());
        verify(itemRepository,times(1)).findByCategoryAndPriceBetween("Electronics",2000,100000);
    }
}