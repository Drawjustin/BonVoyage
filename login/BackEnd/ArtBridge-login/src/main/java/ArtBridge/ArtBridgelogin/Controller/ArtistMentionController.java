package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import ArtBridge.ArtBridgelogin.service.ArtistMentionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artistMentions")
@RequiredArgsConstructor
public class ArtistMentionController {

    private final ArtistMentionService artistMentionService;

    @GetMapping
    public ResponseEntity<List<ArtistMention>> readAllArtistMentions() {
        List<ArtistMention> artistMentions = artistMentionService.readAllArtistsMention();
        return ResponseEntity.ok(artistMentions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistMention> readArtistMention(@PathVariable Long id) {
        ArtistMention artistMention = artistMentionService.readOne(id);
        return ResponseEntity.ok(artistMention);
    }

    @PostMapping
    public ResponseEntity<ArtistMention> createArtistMention(@RequestBody ArtistMention artistMention) {
        ArtistMention createdArtistMention = artistMentionService.createArtistMention(artistMention);
        return ResponseEntity.ok(createdArtistMention);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistMention> updateArtistMention(@PathVariable Long id, @RequestBody ArtistMention updatedArtistMention) {
        ArtistMention artistMention = artistMentionService.updateArtistMention(id, updatedArtistMention);
        return ResponseEntity.ok(artistMention);
    }

    @DeleteMapping("/{mentionSeq}")
    public ResponseEntity<Void> deleteArtistMention(@PathVariable Long mentionSeq) {
        artistMentionService.deleteArtistMention(mentionSeq);
        return ResponseEntity.noContent().build();
    }
}
