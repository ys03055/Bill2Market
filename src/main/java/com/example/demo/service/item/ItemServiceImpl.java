package com.example.demo.service.item;

import com.example.demo.model.item.*;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ItemPhotoRepository;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ClientRepository clientRepository;
    private final ItemPhotoRepository itemPhotoRepository;

    @Autowired
    private ItemPhotoServiceImpl itemPhotoService;

    @Override
    public Slice<SimpleItem> findItemList(ItemSearchRequestDTO itemSearchRequestDTO) {
        return itemRepository.findAllByLocation(itemSearchRequestDTO.getLongitude(), itemSearchRequestDTO.getLatitude(), PageRequest.of(itemSearchRequestDTO.getPage(), 10));
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
    public Optional<Item> findItemOne(Integer itemIndex) {
        return itemRepository.findById(itemIndex);
    }

}
