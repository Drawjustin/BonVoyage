package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.Controller.form.UserAcessForm;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
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

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Item> getPopularItems() {
        return itemRepository.findPopularItems();
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Item> getNewItems() {
        return itemRepository.findLastedItems();
    }


    public List<Item> getItemsBySameAuthor(UserAcessForm userAcessForm) {
        if (userAcessForm.getIsArtist() == 1) {
            return itemRepository.getItemsBySameArtist(userAcessForm.getArtist().getArtistSeq());
        } else {
            return itemRepository.getItemsBySameMember(userAcessForm.getMember().getMemberSeq());
        }

    }
}