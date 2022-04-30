package com.example.demo.service.item;

import com.example.demo.model.item.*;
import com.example.demo.model.review.ReviewResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemService {
    public Slice<SimpleItem> findItemList(ItemSearchRequestDTO itemSearchRequestDTO, Integer clientIndex);
    public void saveItem(ItemSaveRequestDTO itemSaveRequest, List<MultipartFile> itemPhotoSaveRequest) throws IOException;
    public ItemDetailResponseDTO findItemOne(Integer itemId, Integer clientIndex);
    public Slice<ReviewResponseDTO> findItemReview(Integer itemId, Integer page);
    public Slice<SimpleItem> findItemByQuery(ItemSearchRequestDTO itemSearchRequestDTO, Integer clientIndex);
    public List<ItemOwnerResponseDTO> findItemListByClientIndex(Integer clientIndex, Integer ownerId, Pageable pageable);
}
