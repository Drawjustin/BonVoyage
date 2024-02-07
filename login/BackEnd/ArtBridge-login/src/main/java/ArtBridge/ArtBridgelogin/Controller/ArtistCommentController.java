package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import ArtBridge.ArtBridgelogin.service.ArtistMentionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/artistMention")
@RequiredArgsConstructor
public class ArtistCommentController {


    @Autowired
    private final ArtistMentionService artistMentionService;

    @GetMapping
    public List<ArtistMention> readAllMArtistMention() {
        return artistMentionService.readAllArtistsMention();
    }

    @GetMapping("/{id}")
    public ArtistMention readArtistMentionById(@PathVariable Long id) {
        return artistMentionService.readOne(id);
    }

    @PostMapping("/new")
    public ArtistMention createArtistMention(@RequestBody ArtistMention artistMention) {
        return artistMentionService.createArtistMention(artistMention);
    }

    @PutMapping("/{id}")
    public ArtistMention updateArtistMention(@PathVariable Long id, @RequestBody ArtistMention updatedArtistMention) {
        return artistMentionService.updateArtistMention(id, updatedArtistMention);
    }

    @DeleteMapping("/{id}")
    public void deleteArtistMention(@PathVariable Long id) {
        artistMentionService.deleteArtistMention(id);
    }
}
