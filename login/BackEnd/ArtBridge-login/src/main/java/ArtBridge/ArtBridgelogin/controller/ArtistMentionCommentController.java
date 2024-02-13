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

    @GetMapping
    public ResponseEntity<List<ArtistMentionCommentDto>> readAllArtistMentionComments() {
        try {
            List<ArtistMentionCommentDto> artistMentionComments = artistMentionCommentService.readAllArtistMentionComments();
            return ResponseEntity.ok(artistMentionComments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistMentionCommentDto> readArtistMentionCommentById(@PathVariable Long id) {
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

    @PostMapping("/new")public ResponseEntity<ArtistMentionCommentDto> createArtistMentionComment(@RequestBody ArtistMentionCommentDto artistMentionCommentDto) {
                try {
            ArtistMentionCommentDto createdArtistMentionComment = artistMentionCommentService.createArtistMentionComment(artistMentionCommentDto);
//            System.out.println(createdArtistMentionComment.toString());
            return ResponseEntity.ok(createdArtistMentionComment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistMentionCommentDto> updateArtistMentionComment(@PathVariable Long id, @RequestBody ArtistMentionCommentDto updatedArtistMentionCommentDto) {
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
    public ResponseEntity<Void> deleteArtistMentionComment(@PathVariable Long id) {
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
