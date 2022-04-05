package com.example.demo.service.item;

import com.example.demo.model.item.*;
import org.springframework.data.domain.Slice;

import java.io.IOException;
import java.util.Optional;

public interface ItemService {

    public Slice<SimpleItem> findItemList(ItemSearchRequest itemSearchRequest);
    public void saveItem(ItemSaveRequest itemSaveRequest, ItemPhotoSaveRequest itemPhotoSaveRequest) throws IOException;
    public Optional<Item> findItemOne(Integer itemIndex);

}
