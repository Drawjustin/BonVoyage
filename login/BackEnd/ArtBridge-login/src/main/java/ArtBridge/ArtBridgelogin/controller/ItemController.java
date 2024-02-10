package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.controller.dto.item.ItemDto;
import ArtBridge.ArtBridgelogin.controller.form.UserAcessForm;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{seq}")
    public Item readItemBySeq(@PathVariable int seq) {
        return itemService.readItemBySeq(seq);
    }

    @PostMapping("/new")
    public Item createItem(@RequestBody Item item) {

        System.out.println(item);
        return itemService.createItem(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable int id, @RequestBody Item updatedItem) {
        return itemService.updateItem(id, updatedItem);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {
        itemService.deleteItem(id);
    }

    //작품 작가가 만든 다른 상품들도 조회한다.
    //TODO: join 해결 이후 진행 필요
    @GetMapping("/mypage/{authorId}")
    public List<Item> readItemsByAuthor(@PathVariable("authorId") UserAcessForm userAcessForm) {
        return itemService.readItemsBySameAuthor(userAcessForm);
    }


    //TODO: 정렬하는 방법

}
