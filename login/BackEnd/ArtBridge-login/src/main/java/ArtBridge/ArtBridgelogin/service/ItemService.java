package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
import ArtBridge.ArtBridgelogin.controller.dto.item.ItemDto;
import ArtBridge.ArtBridgelogin.controller.form.UserAcessForm;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.repository.ItemRepository;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    //Todo: CREATE
    @Transactional
    public Item createItem(Item item) {
        return itemRepository.create(item);
    }


    //Todo: READ
    public List<ItemDto> readAllItems() {
        List<Item> items = itemRepository.readAll();
        return convertToDtoList(items);
    }

    // 정렬된 아이템 조회 비즈니스 로직
    public List<ItemDto> readAllItemsSorted(String sort) {
        List<Item> items;

        if ("name".equalsIgnoreCase(sort)) {
            items = itemRepository.readAllSortedByName();
        } else if ("price".equalsIgnoreCase(sort)) {
            items = itemRepository.readAllSortedByPrice();
        } else {
            items = itemRepository.readAll();
        }

        // 찾은 아이템이 없을 경우 에러 발생
        if (items.isEmpty()) {
            throw new NoDataFoundException("No items found");
        }

        return convertToDtoList(items);
    }

    // Item을 ItemDto로 변환하는 비즈니스 로직
    private List<ItemDto> convertToDtoList(List<Item> items) {
        return items.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Item> readPopularItems() {
        return itemRepository.readPopularItems();
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Item> readNewItems() {
        return itemRepository.readLastedItems();
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Item readItemBySeq(int seq) {
        return itemRepository.readBySeq(seq);
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Item> readItemsBySameAuthor(UserAcessForm userAcessForm) {
        if (userAcessForm.getIsArtist() == 1) {
            return itemRepository.readItemsBySameArtist(userAcessForm.getArtist().getArtistSeq());
        } else {
            return itemRepository.readItemsBySameMember(userAcessForm.getMember().getMemberSeq());
        }

    }

    //Todo: UPDATE

    @Transactional
    public Item updateItem(int itemSeq, Item updatedItem) {
        return itemRepository.readAndUpdateItem(itemSeq, updatedItem);
    }

    //Todo: DELETE

    @Transactional
    public void deleteItem(int itemSeq) {
        itemRepository.deleteById(itemSeq);
    }


    //Todo: 함수
    private ItemDto convertToDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemName(item.getItemName());
        itemDto.setItemWidth(item.getItemWidth());
        itemDto.setItemHeight(item.getItemHeight());
        itemDto.setItemLike(item.getItemLike());
        itemDto.setItemSellPrice(item.getItemSellPrice());
        itemDto.setItemIsSold(item.isItemIsSold());

        // Artist 정보를 가져와서 설정
        if (item.getArtist() != null) {
            ArtistDto artistDto = new ArtistDto();
            artistDto.setId(item.getArtist().getArtistId());
            itemDto.setArtist(artistDto);
        }
        return itemDto;
    }
}