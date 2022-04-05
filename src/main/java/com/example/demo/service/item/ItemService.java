package com.example.demo.service.item;

import com.example.demo.model.item.ItemSearchRequestDTO;
import com.example.demo.model.item.Item;
import com.example.demo.model.item.ItemSaveRequest;
import com.example.demo.model.item.SimpleItem;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface ItemService {

    public Slice<SimpleItem> findItemList(ItemSearchRequestDTO itemSearchRequestDTO);
    public void saveItem(ItemSaveRequest itemSaveRequest);
    public Optional<Item> findItemOne(Integer itemIndex);

}
