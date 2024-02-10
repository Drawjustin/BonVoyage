package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.item.ItemDto;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.repository.ItemRepository;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void readAllItems_ReturnsListOfItemDtos() {
        // Arrange
        Item item1 = new Item();

        Item item2 = new Item();

        List<Item> items = Arrays.asList(item1, item2);
        when(itemRepository.readAll()).thenReturn(items);

        List<ItemDto> result = itemService.readAllItems();

        assertEquals(items.size(), result.size());
    }

    @Test
    public void readAllItems_ThrowsNoDataFoundExceptionWhenListIsEmpty() {
        when(itemRepository.readAll()).thenReturn(Arrays.asList());

        assertThrows(NoDataFoundException.class, () -> itemService.readAllItems());
    }

    @Test
    public void readItemDtoBySeq_ReturnsItemDto() {
        int seq = 1;
        Item item = new Item();

        when(itemRepository.readBySeq(seq)).thenReturn(item);

        ItemDto result = itemService.readItemDtoBySeq(seq);

        assertEquals(itemService.convertToDto(item), result);
    }

    @Test
    public void readItemDtoBySeq_ThrowsNoDataFoundExceptionWhenItemIsNull() {
        int seq = 1;
        when(itemRepository.readBySeq(seq)).thenReturn(null);

        assertThrows(NoDataFoundException.class, () -> itemService.readItemDtoBySeq(seq));
    }

}
