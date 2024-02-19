package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.controller.dto.LoginReturnForm;
import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistLoginForm;
import ArtBridge.ArtBridgelogin.controller.dto.member.MemberLoginForm;
import ArtBridge.ArtBridgelogin.service.ArtistService;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private MemberLoginForm memberForm;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> readAllArtists() {
        try {
            List<ArtistDto> artistDtos = artistService.readAllArtists();
            return ResponseEntity.ok().body(artistDtos);
            // 추가 실패 시 500 Internal Server Error와 함께 실패 메시지 반환
        } catch (NoDataFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody ArtistLoginForm artistLoginForm) {

        LoginReturnForm loginResult = artistService.login(artistLoginForm.getId(), artistLoginForm.getPw());

        if (loginResult.getSeq() != null) {
            // 로그인 성공
            return ResponseEntity.ok().body(loginResult);
        } else {
            // 로그인 실패
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readArtistById(@PathVariable(value = "id") String id) {
        ArtistDto artistDto = artistService.readOne(id);

        if (artistDto != null) {
            // 조회 성공 시 200 OK와 함께 메시지 반환
            return ResponseEntity.ok(artistDto);
        } else {
            // 추가 실패 시 500 Internal Server Error와 함께 실패 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/new")
    public ResponseEntity<?> createArtist(@RequestBody ArtistDto artistDto) {
        System.out.println(artistDto);
        ArtistDto createdArtist = artistService.createArtist(artistDto);

        if (createdArtist != null) {
            // 추가 성공 시 200 OK와 함께 생성된 Artist 반환
            return ResponseEntity.ok("Artist Created" + createdArtist);
        } else {
            // 추가 실패 시 500 Internal Server Error와 함께 실패 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create artist");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArtist(@PathVariable(value = "id") String id, @RequestBody ArtistDto updatedArtist) {
        ArtistDto artistDto = artistService.updateArtist(id, updatedArtist);

        if (artistDto != null) {
            // 업데이트 성공 시 200 OK와 함께 메시지 반환
            return ResponseEntity.ok("Artist updated" + artistDto);
        } else {
            // 추가 실패 시 500 Internal Server Error와 함께 실패 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Artist not found with ID: " + id);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable(value = "seq") long seq) {
        boolean deleted = artistService.deleteArtist(seq);

        if (deleted) {
            // 삭제 성공 시 200 OK와 함께 메시지 반환
            return ResponseEntity.ok("Artist deleted");
        } else {
            // 추가 실패 시 500 Internal Server Error와 함께 실패 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Artist not found with seq: " + seq);
        }
    }
}
