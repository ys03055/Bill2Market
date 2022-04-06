package com.example.demo.service.item;

import com.example.demo.model.item.ItemSearchRequestDTO;
import com.example.demo.model.item.Item;
import com.example.demo.model.item.ItemSaveRequest;
import com.example.demo.model.item.SimpleItem;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ClientRepository clientRepository;

    @Override
    public Slice<SimpleItem> findItemList(ItemSearchRequestDTO itemSearchRequestDTO) {
        return itemRepository.findAllByLocation(itemSearchRequestDTO.getLongitude(), itemSearchRequestDTO.getLatitude(), PageRequest.of(itemSearchRequestDTO.getPage(), 10));
    }

    @Override
    public void saveItem(ItemSaveRequest itemSaveRequest) {
        itemRepository.save(itemSaveRequest.toEntity());
    }

    @Override
    public Optional<Item> findItemOne(Integer itemIndex) {
        return itemRepository.findById(itemIndex);
    }

}
