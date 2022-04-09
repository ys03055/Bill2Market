package com.example.demo.service.item;

import com.example.demo.model.item.*;
import com.example.demo.model.review.ReviewResponseDTO;
import org.springframework.data.domain.Slice;

import java.io.IOException;
import java.util.Optional;

public interface ItemService {
    public Slice<SimpleItem> findItemList(ItemSearchRequestDTO itemSearchRequestDTO, Integer clientIndex);
    public void saveItem(ItemSaveRequest itemSaveRequest, ItemPhotoSaveRequest itemPhotoSaveRequest) throws IOException;
    public ItemDetailResponseDTO findItemOne(Integer itemId, Integer clientIndex);
    public Slice<ReviewResponseDTO> findItemReview(Integer itemId, Integer page);

}
