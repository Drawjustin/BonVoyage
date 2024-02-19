package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistHomepageCommentDto;
import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistMentionCommentDto;
import ArtBridge.ArtBridgelogin.domain.ArtistHomepageComment;
import ArtBridge.ArtBridgelogin.service.ArtistHomepageCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/artistHomepageComment")
public class ArtistHomepageCommentController {

    @Autowired
    private ArtistHomepageCommentService artistHomepageCommentService;

    @GetMapping
    public ResponseEntity<List<ArtistHomepageCommentDto>> readAlLArtistHomepageComment() {
        return ResponseEntity.ok(artistHomepageCommentService.readAllArtistsHomepageComment());
    }
    @GetMapping("/{seq}")
    public ResponseEntity<List<ArtistHomepageCommentDto>> readAlLHomepageCommentByArtist(@PathVariable(value = "seq") Long Seq) {
        return ResponseEntity.ok(artistHomepageCommentService.readAlLHomepageCommentByArtist(Seq));
    }
    @GetMapping("/{seq}/one")
    public ResponseEntity<?> readArtistMentionById(@PathVariable(value = "seq") Long seq) {
        return ResponseEntity.ok(artistHomepageCommentService.readOne(seq));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createArtistHomepageComment(@RequestBody ArtistHomepageCommentDto artistHomepageComment) {

       artistHomepageCommentService.createArtistHomepageComment (artistHomepageComment);
        return ResponseEntity.ok("artistHomepageComment Create");
    }

    @PutMapping("/{seq}")
    public ResponseEntity<?> updateArtistHomepageComment(@PathVariable(value = "seq") Long seq, @RequestBody ArtistHomepageCommentDto updatedArtistHomepageComment) {
        return ResponseEntity.ok(artistHomepageCommentService.updateArtistHomepageComment(seq, updatedArtistHomepageComment));
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<?> deleteArtistHomepageComment(@PathVariable(value = "seq") Long seq) {
        artistHomepageCommentService.deleteArtistHomepageComment(seq);
        return ResponseEntity.ok("Delete");
    }
}
