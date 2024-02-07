package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionRepository;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ArtBridge.ArtBridgelogin.domain.ArtistMention;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ArtistMentionServiceTest {
    @Autowired
    ArtistMentionService artistMentionService;

    @Autowired
    ArtistMentionRepository artistMentionRepository;

    @Autowired
    ArtistRepository artistRepository;

    private ArtistMention artistMention;

    @BeforeEach
    public void setup() {
        Artist artist = artistRepository.readArtistById("123");
        this.artistMention = new ArtistMention();

        artistMention.setArtistMentionContent("Content");
        artistMention.setArtistMentionSubject("Subject");  // artistMentionSubject 필드를 설정합니다.
        artistMention.setArtistMentionCreatedDate(LocalDateTime.now());
        artistMention.setArtist(artist);
    }

    @Test
    public void getAllArtistsMention() {
        List<ArtistMention> artistMentions = artistMentionService.readAllArtistsMention();
        assertNotNull(artistMentions);
    }

    @Test
    public void readOne() {
        artistMentionService.createArtistMention(artistMention);
        Long id = artistMention.getArtistMentionSeq();  // 데이터베이스에 존재하는 ArtistMention의 id
        ArtistMention artistMention = artistMentionService.readOne(id);
        assertNotNull(artistMention);
    }

    @Test
    public void createArtistMention() {
        ArtistMention createdArtistMention = artistMentionService.createArtistMention(artistMention);
        assertNotNull(createdArtistMention);
    }

    @Test
    public void updateArtistMention() {
        artistMentionService.createArtistMention(artistMention);
        Long id = artistMention.getArtistMentionSeq();

        artistMention.setArtistMentionContent("하하하");
        ArtistMention updatedArtistMention = artistMentionService.updateArtistMention(id, artistMention);
        assertNotNull(updatedArtistMention);
        // 추가적으로 artistMention 객체의 속성 값 등에 대한 검증을 수행할 수 있습니다.
    }

    @Test
    public void deleteArtistMention() {
        artistMentionService.createArtistMention(artistMention);
        Long mentionSeq = artistMention.getArtistMentionSeq();  // 데이터베이스에 존재하는 ArtistMention의 mentionSeq
        artistMentionService.deleteArtistMention(mentionSeq);

        assertThrows(InvalidDataAccessApiUsageException.class, () -> artistMentionService.readOne(mentionSeq));
    }
}
