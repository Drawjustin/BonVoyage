package ArtBridge.ArtBridgelogin.test;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import ArtBridge.ArtBridgelogin.domain.ArtistMentionComment;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionCommentRepository;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionRepository;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.not;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ArtistMentionCommentServiceTest {
    @Autowired
    ArtistMentionCommentService artistMentionCommentService;

    @Autowired
    ArtistMentionCommentRepository artistMentionCommentRepository;

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    ArtistMentionRepository artistMentionRepository;

    private static ArtistMentionComment artistMentionComment;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setup() {
        artistMentionComment = new ArtistMentionComment();
        Artist artist = artistRepository.readArtistById("123");
        ArtistMention artistMention = artistMentionRepository.readAll().get(0);

        artistMentionComment.setArtistMention(artistMention);
        artistMentionComment.setArtistMentionCommentCreatedDate(LocalDateTime.now());
        artistMentionComment.setArtistMentionCommentContent("Test Comment");

        artistMentionCommentService.createArtisMentioneComment(artistMentionComment);
    }


    @Test
    public void testreadOne() {
        assertNotNull(artistMentionCommentService.readOne(artistMentionComment.getArtistMentionCommentSeq()));
    }

    @Test
    public void testGetAllArtistsMentionComment() {
        List<ArtistMentionComment> comments = artistMentionCommentService.readAllArtistsMentionComment();
        assertTrue(comments.size() > 0);
    }


    @Test
    public void testUpdateArtistMentionComment() {
        ArtistMentionComment updatedComment = new ArtistMentionComment();
        updatedComment.setArtistMentionCommentContent("Updated Comment");
        artistMentionCommentService.updateArtistMentionComment(artistMentionComment.getArtistMentionCommentSeq(), updatedComment);

        ArtistMentionComment comment = artistMentionCommentService.readOne(artistMentionComment.getArtistMentionCommentSeq());
        assertEquals("Updated Comment", comment.getArtistMentionCommentContent());
    }
}
