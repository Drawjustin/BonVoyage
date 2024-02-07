package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
import ArtBridge.ArtBridgelogin.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    EntityManager em;

    private Item createTestItem() {
        Item item = new Item();

        Artist artist = artistRepository.readArtistById("123");

        item.setItemName("별이 빛나는 밤");
        item.setItemWidth(123);
        item.setItemHeight(123);
        item.setItemLike(123);
        item.setItemSellPrice(123L);
        item.setItemIsSold(false);
        item.setItemCreatedDate(LocalDateTime.now());

        item.setArtist(artist);
        return item;
    }

    @Test
    public void 아이템_생성() {
        Item testItem = createTestItem();

        //when
        Item createdItem = itemService.createItem(testItem);

        //then
        assertEquals(createdItem, itemRepository.readBySeq(createdItem.getItemSeq()));
    }

    @Test
    public void 아이템_조회() {
        Item testItem = createTestItem();

        //given
        Item createdItem = itemService.createItem(testItem);

        //when
        Item foundItem = itemService.readItemBySeq(createdItem.getItemSeq());

        //then
        assertEquals(createdItem, foundItem);
    }

    @Test
    public void 아이템_수정() {
        Item testItem = createTestItem();

        //given
        Item createdItem = itemService.createItem(testItem);

        //when
        String newName = "별 헤는 밤";
        createdItem.setItemName(newName);
        itemService.updateItem(createdItem.getItemSeq(), createdItem);

        //then
        assertEquals(newName, itemRepository.readBySeq(createdItem.getItemSeq()).getItemName());
    }

    @Test
    public void 아이템_삭제() {
        Item testItem = createTestItem();

        //given
        Item createdItem = itemService.createItem(testItem);

        //when
        itemService.deleteItem(createdItem.getItemSeq());

        //then

        assertNull(itemRepository.readBySeq(createdItem.getItemSeq()));
    }
}
