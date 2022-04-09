package com.example.demo.service.item;

import com.example.demo.model.item.*;
import org.springframework.data.domain.Slice;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ItemService {
    public Slice<SimpleItem> findItemList(ItemSearchRequestDTO itemSearchRequestDTO);
    public void saveItem(ItemSaveRequest itemSaveRequest, List<MultipartFile> itemPhotoSaveRequest) throws IOException;
    public Optional<Item> findItemOne(Integer itemIndex);

}
