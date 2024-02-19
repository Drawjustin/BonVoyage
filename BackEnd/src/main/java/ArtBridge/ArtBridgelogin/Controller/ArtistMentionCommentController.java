package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistMentionCommentDto;
import ArtBridge.ArtBridgelogin.service.ArtistMentionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/artistMentionComment")
@RequiredArgsConstructor
public class ArtistMentionCommentController {

    private final ArtistMentionCommentService artistMentionCommentService;

    // ArtistMention id를 통해 아래에 달려있는 ArtistMentionComment 리스트 조회
    @GetMapping("/{id}")
    public ResponseEntity<List<ArtistMentionCommentDto>> readArtistMentionCommentByMentionId(@PathVariable(value = "id") Long id) {
        try {
            List<ArtistMentionCommentDto> artistMentionCommentDtoList = artistMentionCommentService.readArtistMentionCommentByMentionId(id);
            if (artistMentionCommentDtoList != null) {
                return ResponseEntity.ok(artistMentionCommentDtoList);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // ArtistMentionComment id를 통해 단건 댓글 조회
    @GetMapping("/reply/{id}")
    public ResponseEntity<ArtistMentionCommentDto> readArtistMentionCommentById(@PathVariable(value = "id") Long id) {
        try {
            ArtistMentionCommentDto artistMentionComment = artistMentionCommentService.readArtistMentionCommentById(id);
            if (artistMentionComment != null) {
                return ResponseEntity.ok(artistMentionComment);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<ArtistMentionCommentDto> createArtistMentionComment(@RequestBody ArtistMentionCommentDto artistMentionCommentDto) {
                try {
                    System.out.println(artistMentionCommentDto.toString());
                    ArtistMentionCommentDto createdArtistMentionComment = artistMentionCommentService.createArtistMentionComment(artistMentionCommentDto);
            return ResponseEntity.ok(createdArtistMentionComment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistMentionCommentDto> updateArtistMentionComment(@PathVariable(value = "id") Long id, @RequestBody ArtistMentionCommentDto updatedArtistMentionCommentDto) {
        try {
            ArtistMentionCommentDto artistMentionComment = artistMentionCommentService.updateArtistMentionComment(id, updatedArtistMentionCommentDto);
            if (artistMentionComment != null) {
                return ResponseEntity.ok(artistMentionComment);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtistMentionComment(@PathVariable(value = "id") Long id) {
        try {
            boolean deleted = artistMentionCommentService.deleteArtistMentionComment(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
