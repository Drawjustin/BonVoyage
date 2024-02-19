package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
import ArtBridge.ArtBridgelogin.controller.dto.item.ItemDto;
import ArtBridge.ArtBridgelogin.controller.dto.wish.WishDto;
import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.domain.Wish;
import ArtBridge.ArtBridgelogin.repository.ItemRepository;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    @Autowired
    private ArtistService artistService;

    //Todo: CREATE
    @Transactional
    public List<ItemDto> readAllItems() {
        List<Item> items = itemRepository.readAll();
        if (items.isEmpty()) {
            throw new NoDataFoundException("No items found");
        }
        return convertToDtoList(items);
    }

    @Transactional
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

    @Transactional
    public ItemDto readItemDtoBySeq(int seq) {
        Item item = itemRepository.readBySeq(seq);
        if (item == null) {
            throw new NoDataFoundException("Item not found with seq: " + seq);
        }
        return convertToDto(item);
    }

    @Transactional
    public ItemDto createItemDto(ItemDto itemDto) {

        Item newItem = convertToEntity(itemDto);

        newItem.setArtist(artistService.readArtistById(itemDto.getArtistId()));

        newItem.setItemName(itemDto.getItemName());
        newItem.setExplain(itemDto.getExplain());
        newItem.setItemWidth(itemDto.getItemWidth());
        newItem.setItemHeight(itemDto.getItemHeight());
        newItem.setItemLike(itemDto.getItemLike());
        newItem.setItemSellPrice(itemDto.getItemSellPrice());
        newItem.setItemCreatedDate(LocalDateTime.now());

        Item createdItem = itemRepository.create(newItem);




        return convertToDto(createdItem);
    }

    @Transactional
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
    @Transactional
    public Wish updateItemStatus(WishDto wishDto) {
        return itemRepository.readByMemberSeq(wishDto.getItem(),wishDto.getMember());

    }
    @Transactional
    public void deleteItem(int id) {
        Item existingItem = itemRepository.readBySeq(id);
        if (existingItem == null) {
            throw new NoDataFoundException("Item not found with id: " + id);
        }

        itemRepository.deleteById(id);
    }

    @Transactional
    public ItemDto convertToDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemSeq(item.getItemSeq());
        itemDto.setItemName(item.getItemName());
        itemDto.setItemWidth(item.getItemWidth());
        itemDto.setItemHeight(item.getItemHeight());
        itemDto.setItemLike(item.getItemLike());
        itemDto.setItemSellPrice(item.getItemSellPrice());
        itemDto.setItemIsSold(item.isItemIsSold());
        itemDto.setExplain(item.getExplain());

        // Map ArtistDto
        if (item.getArtist() != null) {
            ArtistDto artistDto = new ArtistDto();
            artistDto.setId(item.getArtist().getArtistId());
            itemDto.setArtistId(artistDto.getId());
        }

        return itemDto;
    }

    @Transactional
    public List<ItemDto> convertToDtoList(List<Item> items) {
        return items.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Item convertToEntity(ItemDto itemDto) {
        Item item = new Item();
        item.setItemName(itemDto.getItemName());
        item.setItemWidth(itemDto.getItemWidth());
        item.setItemHeight(itemDto.getItemHeight());
        item.setItemLike(itemDto.getItemLike());
        item.setItemSellPrice(itemDto.getItemSellPrice());
        item.setItemIsSold(itemDto.isItemIsSold());

        // Map Artist entity
        if (itemDto.getArtistId() != null) {
            Artist artist = new Artist();
            artist.setArtistId(itemDto.getArtistId());  // Assuming you have setId method in ArtistDto
            item.setArtist(artist);
        }

        return item;
    }

}