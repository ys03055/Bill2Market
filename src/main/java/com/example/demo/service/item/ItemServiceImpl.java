package com.example.demo.service.item;

import com.example.demo.exception.client.ClientNotFoundException;
import com.example.demo.exception.item.ItemNotFoundException;
import com.example.demo.model.item.*;
import com.example.demo.model.review.ReviewResponseDTO;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ClientRepository clientRepository;
    private final ItemPhotoRepository itemPhotoRepository;
    private final BasketRepository basketRepository;
    private final ReviewRepository reviewRepository;
    private final ItemPhotoServiceImpl itemPhotoService;

    @Override
    public Slice<SimpleItem> findItemList(ItemSearchRequestDTO itemSearchRequestDTO, Integer clientIndex) {
        return itemRepository.findAllByLocation(itemSearchRequestDTO.getLongitude(), itemSearchRequestDTO.getLatitude(), clientIndex, PageRequest.of(itemSearchRequestDTO.getPage(), 10));
    }

    @Override
    public void saveItem(ItemSaveRequest itemSaveRequest, List<MultipartFile> itemPhotoSaveRequest) throws IOException {
        Item item = itemRepository.save(itemSaveRequest.toEntity());
        List<String> photoUrls = itemPhotoService.upload(itemPhotoSaveRequest, "itemPhoto");
        boolean isMain = true;
        for(String url: photoUrls) {
            itemPhotoRepository.save(ItemPhoto.builder().itemId(item.getItemId()).itemPhoto(url).isMain(isMain).build());
            isMain = false;
        }

    }

    @Override
    public ItemDetailResponseDTO findItemOne(Integer itemId, Integer clientIndex) {
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        return ItemDetailResponseDTO.builder()
                .ownerInfo(clientRepository.findOwnerInfoByClientIndex(item.getOwnerId()).orElseThrow(ClientNotFoundException::new))
                .item(item)
                .basketCount(basketRepository.countByItemId(itemId))
                .isLike(basketRepository.existsBasketByBasketPK(itemId, clientIndex) == 1? true : false)
                .build();
    }

    @Override
    public Slice<ReviewResponseDTO> findItemReview(Integer itemId, Integer page) {
        return reviewRepository.findSliceByItemId(itemId, PageRequest.of(page, 10));
    }

}
