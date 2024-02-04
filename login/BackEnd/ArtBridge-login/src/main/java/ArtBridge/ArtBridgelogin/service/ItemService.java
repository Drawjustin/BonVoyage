package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Item getItemBySeq(int seq) {
        return itemRepository.findBySeq(seq);
    }

    @Transactional
    public Item createItem(Item item) {
        return itemRepository.create(item);
    }

    @Transactional
    public Item updateItem(int itemSeq, Item updatedItem) {
        return itemRepository.findAndUpdateItem(itemSeq, updatedItem);
    }

    @Transactional
    public void deleteItem(int itemSeq) {
        itemRepository.deleteById(itemSeq);
    }

    public List<Item> getPopularItems() {
        return itemRepository.findPopularItems();
    }

    public List<Item> getNewItems() {
        return itemRepository.findLastedItems();
    }

    //TODO: Implement this method after resolving artist_seq join
//    public List<Item> getItemsBySameAuthor(String authorId) {
//        return itemRepository.findSameAuthorItems(authorId);
//    }
}
