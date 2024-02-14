package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistMentionDto;
import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import ArtBridge.ArtBridgelogin.service.ArtistMentionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/artistMentions")
@RequiredArgsConstructor
public class ArtistMentionController {

    private final ArtistMentionService artistMentionService;

    @GetMapping
    public ResponseEntity<List<ArtistMentionDto>> readAllArtistMentions() {
        try {
            List<ArtistMentionDto> artistMentions = artistMentionService.readAllArtistsMention();
            return ResponseEntity.ok(artistMentions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistMentionDto> readArtistMention(@PathVariable(value = "id") Long id) {
        try {
            ArtistMentionDto artistMention = artistMentionService.readOne(id);
            return ResponseEntity.ok(artistMention);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<ArtistMentionDto> createArtistMention(@RequestBody ArtistMentionDto artistMentionDto) {
        try {
            ArtistMentionDto createdArtistMention = artistMentionService.createArtistMention(artistMentionDto);
            return ResponseEntity.ok(createdArtistMention);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistMentionDto> updateArtistMention(@PathVariable(value = "id") Long id, @RequestBody ArtistMentionDto updatedArtistMentionDto) {
        try {
            ArtistMentionDto artistMention = artistMentionService.updateArtistMention(id, updatedArtistMentionDto);
            return ResponseEntity.ok(artistMention);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{mentionSeq}")
    public ResponseEntity<Void> deleteArtistMention(@PathVariable(value = "mentionSeq") Long mentionSeq) {
        try {
            artistMentionService.deleteArtistMention(mentionSeq);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
