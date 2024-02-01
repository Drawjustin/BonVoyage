package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.service.ArtistService;
import ArtBridge.ArtBridgelogin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<?> getAllItems(@RequestParam(required = false) String sort) {
        List<Item> items;

        if (sort == null) {
            // 전체 아이템 조회 로직
            items = itemService.getAllItems();
        } else if (sort.equals("popular")) {
            // 인기 아이템 조회 로직
            items = itemService.getPopularItems();
        } else if (sort.equals("new")) {
            // 최신 아이템 조회 로직
            items = itemService.getNewItems();
        } else {
            return new ResponseEntity<>("sort값이 잘못 들어왔습니다.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable int id) {
        return itemService.getItemByID(id);
    }

    @PostMapping("/new")
    public Item createItem(@RequestBody Item item) {
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
//    @GetMapping("/{authorId}")
//    public List<Item> getItemsByAuthor(@PathVariable String authorId) {
//        return itemService.getItemsBySameAuthor(authorId);
//    }

    //TODO: 작품 좋아요를 추가한다???
}
