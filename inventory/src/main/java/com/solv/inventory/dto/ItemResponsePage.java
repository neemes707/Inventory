package com.solv.inventory.dto;

import com.solv.inventory.entity.Item;
import lombok.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponsePage {
    List<Item> itemList;
    int pageNumber;
    int pageSize;
    int totalPage;
    boolean isLastPage;
}
