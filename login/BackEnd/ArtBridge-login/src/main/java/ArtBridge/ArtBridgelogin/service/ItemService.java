package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
import ArtBridge.ArtBridgelogin.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Item getItemByName(String name) {
        return itemRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Item getItemByID(int id) {
        return itemRepository.findById(id);
    }

    @Transactional
    public Item createItem(Item item) {
        return itemRepository.create(item);
    }
    @Transactional
    public Item updateItem(int itemSeq, Item updatedItem) {
        Item existingItem = itemRepository.findById(itemSeq);

        if (existingItem != null) {
            // 업데이트할 정보를 새로운 정보로 설정
            existingItem.setItemName(updatedItem.getItemName());
            existingItem.setItemHeight(updatedItem.getItemHeight());
            existingItem.setItemWidth(updatedItem.getItemWidth());
            //existingItem.setItemLike(updatedItem.setItemLike());
            existingItem.setItemIsSold(updatedItem.isItemIsSold());
            existingItem.setItemSellPrice(updatedItem.getItemSellPrice());

            // 저장
            itemRepository.create(existingItem);
            return existingItem;
        } else {
            // 예외 처리 또는 적절한 로직 추가
            return null;
        }
    }

    @Transactional
    public void deleteItem(int itemSeq) {
        itemRepository.deleteById(itemSeq);
    }

    public List<Item> getPopularItems() {
        return itemRepository.findPopularItems();
    }

    public List<Item> getNewItems() {
        return itemRepository.findNewItems();
    }

    //TODO: artist_seq 조인 해결 이후
//    public List<Item> getItemsBySameAuthor(String authorId) {
//
//        return itemRepository.findSameAuthorItems();
//    }
}
