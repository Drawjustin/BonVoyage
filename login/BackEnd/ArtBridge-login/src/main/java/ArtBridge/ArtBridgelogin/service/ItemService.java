package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
import ArtBridge.ArtBridgelogin.controller.dto.item.ItemDto;
import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.repository.ItemRepository;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    //Todo: CREATE
    public List<ItemDto> readAllItems() {
        List<Item> items = itemRepository.readAll();
        if (items.isEmpty()) {
            throw new NoDataFoundException("No items found");
        }
        return convertToDtoList(items);
    }

    public List<ItemDto> readAllItemsSorted(String sort) {
        List<Item> items;

        if ("name".equalsIgnoreCase(sort)) {
            items = itemRepository.readAllSortedByName();
        } else if ("price".equalsIgnoreCase(sort)) {
            items = itemRepository.readAllSortedByPrice();
        } else {
            items = itemRepository.readAll();
        }

        if (items.isEmpty()) {
            throw new NoDataFoundException("No items found");
        }

        return convertToDtoList(items);
    }

    public ItemDto readItemDtoBySeq(int seq) {
        Item item = itemRepository.readBySeq(seq);
        if (item == null) {
            throw new NoDataFoundException("Item not found with seq: " + seq);
        }
        return convertToDto(item);
    }

    public ItemDto createItemDto(ItemDto itemDto) {
        // 유효성 검사 등 필요한 로직 추가
        // ...

        Item newItem = convertToEntity(itemDto);
        Item createdItem = itemRepository.create(newItem);
        return convertToDto(createdItem);
    }

    public ItemDto updateItem(int id, ItemDto updatedItemDto) {
        // 유효성 검사 등 필요한 로직 추가
        // ...

        Item existingItem = itemRepository.readBySeq(id);
        if (existingItem == null) {
            throw new NoDataFoundException("Item not found with id: " + id);
        }

        // 업데이트 로직 수행
        // ...

        return convertToDto(existingItem);
    }

    public void deleteItem(int id) {
        Item existingItem = itemRepository.readBySeq(id);
        if (existingItem == null) {
            throw new NoDataFoundException("Item not found with id: " + id);
        }

        itemRepository.deleteById(id);
    }

    public ItemDto convertToDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemName(item.getItemName());
        itemDto.setItemWidth(item.getItemWidth());
        itemDto.setItemHeight(item.getItemHeight());
        itemDto.setItemLike(item.getItemLike());
        itemDto.setItemSellPrice(item.getItemSellPrice());
        itemDto.setItemIsSold(item.isItemIsSold());

        // Map ArtistDto
        if (item.getArtist() != null) {
            ArtistDto artistDto = new ArtistDto();
            artistDto.setId(item.getArtist().getArtistId());
            itemDto.setArtist(artistDto);
        }

        return itemDto;
    }

    public List<ItemDto> convertToDtoList(List<Item> items) {
        return items.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Item convertToEntity(ItemDto itemDto) {
        Item item = new Item();
        item.setItemName(itemDto.getItemName());
        item.setItemWidth(itemDto.getItemWidth());
        item.setItemHeight(itemDto.getItemHeight());
        item.setItemLike(itemDto.getItemLike());
        item.setItemSellPrice(itemDto.getItemSellPrice());
        item.setItemIsSold(itemDto.isItemIsSold());

        // Map Artist entity
        if (itemDto.getArtist() != null) {
            Artist artist = new Artist();
            artist.setArtistId(itemDto.getArtist().getId());  // Assuming you have setId method in ArtistDto
            item.setArtist(artist);
        }

        return item;
    }
}