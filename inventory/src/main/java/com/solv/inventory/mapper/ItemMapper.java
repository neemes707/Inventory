package com.solv.inventory.mapper;

import com.solv.inventory.dto.ItemDto;
import com.solv.inventory.entity.Item;
import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {
    public static ItemDto toItemDto(Item item){
        return ItemDto.builder()
                .price(item.getPrice())
                .name(item.getName())
                .category(item.getCategory())
                .build();
    }
    public static Item toItem(ItemDto itemDto){
        return Item.builder()
                .name(itemDto.getName())
                .price(itemDto.getPrice())
                .category(itemDto.getCategory())
                .build();
    }
    public static List<Item> toItem(List<ItemDto> itemDtoList){
        return itemDtoList.stream().map(ItemMapper::toItem).collect(Collectors.toList());
    }
    public static List<ItemDto> toItemDto(List<Item> itemList){
        return itemList.stream().map(ItemMapper::toItemDto).collect(Collectors.toList());
    }
}
