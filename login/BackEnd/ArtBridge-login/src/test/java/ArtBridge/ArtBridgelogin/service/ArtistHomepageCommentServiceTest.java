//package ArtBridge.ArtBridgelogin.service;
//
//import ArtBridge.ArtBridgelogin.domain.*;
//import ArtBridge.ArtBridgelogin.repository.*;
//import jakarta.persistence.EntityManager;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@Transactional
//public class ArtistHomepageCommentServiceTest {
//    @Autowired
//    ArtistHomepageCommentService artistHomepageCommentService;
//
//    @Autowired
//    ArtistHomepageCommentRepository artistHomepageCommentRepository;
//
//    @Autowired
//    ArtistRepository artistRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    private static ArtistHomepageComment artistHomepageComment;
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @BeforeEach
//    public void setup() {
//        artistHomepageComment = new ArtistHomepageComment();
//        Artist artist = artistRepository.readArtistById("123");
//        Member member = memberRepository.readOne(4L);
//
//        artistHomepageComment.setArtist(artist);
//        artistHomepageComment.setMember(member);
//        artistHomepageComment.setArtistHompageCommentContentCreatedDate(LocalDateTime.now());
//        artistHomepageComment.setArtistHompageCommentContent("Test Comment");
//
//        artistHomepageCommentService.createArtistHomepageComment(artistHomepageComment);
//    }
//
//
//    @Test
//    public void testreadOne() {
//        assertNotNull(artistHomepageCommentService.readOne(artistHomepageComment.getArtistHomepageCommentSeq()));
//    }
//
//    @Test
//    public void testGetAllArtistsMentionComment() {
//        List<ArtistHomepageComment> comments = artistHomepageCommentService.readAllArtistsHomepageComment();
//        assertTrue(comments.size() > 0);
//    }
//
//
//    @Test
//    public void testUpdateArtistMentionComment() {
//        ArtistHomepageComment updatedComment = new ArtistHomepageComment();
//        updatedComment.setArtistHompageCommentContent("Updated Comment");
//        artistHomepageCommentService.updateArtistHomepageComment(artistHomepageComment.getArtistHomepageCommentSeq(), updatedComment);
//
//        ArtistHomepageComment comment = artistHomepageCommentService.readOne(artistHomepageComment.getArtistHomepageCommentSeq());
//        assertEquals("Updated Comment", comment.getArtistHompageCommentContent());
//    }
//
//    @Test
//    public void deleteArtistHomepageCommentTest() {
//        Long seq = artistHomepageComment.getArtistHomepageCommentSeq();
//        artistHomepageCommentService.deleteArtistHomepageComment(seq);
//
//        assertNull(artistHomepageCommentRepository.readOne(seq));
//    }
//}
