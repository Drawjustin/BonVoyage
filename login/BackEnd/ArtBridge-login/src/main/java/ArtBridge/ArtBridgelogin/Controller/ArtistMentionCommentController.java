package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.ArtistMentionComment;
import ArtBridge.ArtBridgelogin.service.ArtistMentionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/artistMentionComment")
@RequiredArgsConstructor
public class ArtistMentionCommentController {

    @Autowired
    private ArtistMentionCommentService artistMentionCommentService;

    @GetMapping
    public List<ArtistMentionComment> getAlLArtistMentionComment() {return artistMentionCommentService.getAllArtistsMentionComment();}

    @GetMapping("/{id}")
    public ArtistMentionComment getArtistMentionById(@PathVariable Long id) {return artistMentionCommentService.findOne(id);}

    @PostMapping("/new")
    public ArtistMentionComment createArtistMentionComment(@RequestBody ArtistMentionComment artistMentionComment) {
        return artistMentionCommentService.createArtisMentioneComment(artistMentionComment);
    }

    @PutMapping("/{id}")
    public ArtistMentionComment updateArtistMentionComment(@PathVariable Long id, @RequestBody ArtistMentionComment updatedArtistMentionComment) {
        return artistMentionCommentService.updateArtistMentionComment(id, updatedArtistMentionComment);
    }

    @DeleteMapping("/{id}")
    public void deleteArtistMentionComment(@PathVariable Long id) {
        artistMentionCommentService.deleteArtistMentionComment(id);
    }
}
