package ArtBridge.ArtBridgelogin.test;

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


    //Todo: CREATE
    @Transactional
    public Item createItem(Item item) {
        return itemRepository.create(item);
    }


    //Todo: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Item> readAllItems() {
        return itemRepository.readAll();
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
}