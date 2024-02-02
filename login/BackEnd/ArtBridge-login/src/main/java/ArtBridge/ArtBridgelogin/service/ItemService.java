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
        Item existingItem = itemRepository.findBySeq(itemSeq);

        if (existingItem != null) {
            // Update fields directly
            existingItem.setItemName(updatedItem.getItemName());
            existingItem.setItemHeight(updatedItem.getItemHeight());
            existingItem.setItemWidth(updatedItem.getItemWidth());
            existingItem.setItemIsSold(updatedItem.isItemIsSold());
            existingItem.setItemSellPrice(updatedItem.getItemSellPrice());

            // No need to create a new item, just return the updated item
            return existingItem;
        } else {
            // Handle the case where the item with given id doesn't exist
            // You might throw an exception or return null, depending on your design
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
        return itemRepository.findLastedItems();
    }

    //TODO: Implement this method after resolving artist_seq join
//    public List<Item> getItemsBySameAuthor(String authorId) {
//        return itemRepository.findSameAuthorItems(authorId);
//    }
}
