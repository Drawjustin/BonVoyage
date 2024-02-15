package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.controller.dto.item.ItemDto;
import ArtBridge.ArtBridgelogin.controller.dto.wish.WishDto;
import ArtBridge.ArtBridgelogin.controller.form.UserAcessForm;
import ArtBridge.ArtBridgelogin.domain.Wish;
import ArtBridge.ArtBridgelogin.service.ItemService;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/item")
public class  ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemDto>> readAllItems() {
        try {
            List<ItemDto> items = itemService.readAllItems();
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }
    @GetMapping("/sorted")
    public ResponseEntity<?> readAllItemsSorted(@RequestParam(required = false) String sort) {
        try {
            List<ItemDto> items = itemService.readAllItemsSorted(sort);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }


    @PostMapping("/mypage/perchase")
    public ResponseEntity<?> updateItemstatus(@RequestBody WishDto wishDto) {
        try {
            Wish wish = itemService.updateItemStatus(wishDto);
            return ResponseEntity.ok().body("ok");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @GetMapping("/{seq}")
    public ResponseEntity<?> readItemBySeq(@PathVariable(value = "seq") int seq) {
        try {
            ItemDto itemDto = itemService.readItemDtoBySeq(seq);
            return ResponseEntity.ok(itemDto);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }
    @PostMapping("/new")
    public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto) {
        try {

            ItemDto createdItemDto = itemService.createItemDto(itemDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdItemDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable(value = "id") int id, @RequestBody ItemDto updatedItemDto) {
        try {
            ItemDto itemDto = itemService.updateItem(id, updatedItemDto);
            return ResponseEntity.ok(itemDto);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(value = "id") int id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.noContent().build();
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }

    // 작품 작가가 만든 다른 상품들도 조회한다.
    // TODO: join 해결 이후 진행 필요
    @GetMapping("/mypage/{authorId}")
    public ResponseEntity<?> readItemsByAuthor(@PathVariable("authorId") UserAcessForm userAcessForm) {
        try {
            List<ItemDto> itemDtos = itemService.readAllItems();
            return ResponseEntity.ok(itemDtos);
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
        }
    }

}
