package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ArtistServiceTest {

    @Autowired
    ArtistService artistService;
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    EntityManager em;

    private Artist createArtist(String name, String id, String pwd, String nickname, String email,
                                String contact, long point, boolean isDelete, String content,
                                LocalDateTime deletedDate, LocalDateTime createdDate) {
        Artist artist = new Artist();
        artist.setArtistName(name);
        artist.setArtistId(id);
        artist.setArtistPwd(pwd);
        artist.setArtistNickname(nickname);
        artist.setArtistEmail(email);
        artist.setArtistContact(contact);
        artist.setArtistPoint(point);
        artist.setArtistIsdeleted(isDelete);
        artist.setArtistHistory(content);
        artist.setArtistDeletedDate(deletedDate);
        artist.setArtistCreatedDate(createdDate);
        return artist;
    }

    private Artist artist;

    @BeforeEach
    public void setUp() {
        artist = createArtist("이동훈", "alexander", "1234", "상남자",
                "alexander@gmail.com", "01022369308", 0,
                false, "할수 없어", LocalDateTime.now(), LocalDateTime.now());
    }


    @Test
    public void 회원가입() throws Exception {
        //when
        Artist chArtist = artistService.createArtist(artist);

        //then
        assertEquals(chArtist, artistRepository.findArtistByName(chArtist.getArtistId()));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        artistService.createArtist(artist);

        //when & then
        assertThrows(IllegalStateException.class, () -> {
            artistService.createArtist(artist);
        });
    }
}
