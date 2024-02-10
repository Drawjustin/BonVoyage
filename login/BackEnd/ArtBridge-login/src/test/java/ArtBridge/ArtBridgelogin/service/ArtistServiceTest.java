package ArtBridge.ArtBridgelogin.service;//package ArtBridge.ArtBridgelogin.service;
//
//import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
//import ArtBridge.ArtBridgelogin.domain.Artist;
//import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
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
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@Transactional
//public class ArtistServiceTest {
//
//    @Autowired
//    ArtistService artistService;
//    @Autowired
//    ArtistRepository artistRepository;
//    @Autowired
//    EntityManager em;
//
//
//    private ArtistDto createArtist(String name, String id, String pwd, String nickname, String email,
//                                String contact, long point, boolean isDelete, String content,
//                                LocalDateTime deletedDate, LocalDateTime createdDate) {
//        Artist artist = new Artist();
//        artist.setArtistName(name);
//        artist.setArtistId(id);
//        artist.setArtistPwd(pwd);
//        artist.setArtistNickname(nickname);
//        artist.setArtistEmail(email);
//        artist.setArtistContact(contact);
//        artist.setArtistPoint(point);
//        artist.setArtistIsdeleted(isDelete);
//        artist.setArtistHistory(content);
//        artist.setArtistDeletedDate(deletedDate);
//        artist.setArtistCreatedDate(createdDate);
//
//        ArtistDto artistDto = convertToDto(artist);
//
//        return artistDto;
//    }
//
//    private ArtistDto artistDto;
//
//    @BeforeEach
//    public void setUp() {
//        ArtistDto artistDto = createArtist("이동훈", "hhh", "1234", "상남자",
//                "alexander@gmail.com", "01022369308", 0,
//                false, "할수 없어", LocalDateTime.now(), LocalDateTime.now());
//    }
//
//
//    @Test
//    public void 회원가입() throws Exception {
//        //when
//
//       ArtistDto chArtist = artistService.createArtist(artistDto);
//
//        //then
//        assertEquals(chArtist, artistRepository.readArtistById(chArtist.getId()));
//    }
//
//    @Test
//    public void 중복_회원_예외() throws Exception {
//        //given
//        artistService.createArtist(artistDto);
//
//        //when & then
//        assertThrows(IllegalStateException.class, () -> {
//            artistService.createArtist(artistDto);
//        });
//    }
//
//    private Artist convertToEntity(ArtistDto artistDto) {
//        Artist artist = new Artist();
//        artist.setArtistId(artistDto.getId());
//        artist.setArtistName(artistDto.getName());
//        artist.setArtistPwd(artistDto.getPw());
//        artist.setArtistNickname(artistDto.getNickName());
//        artist.setArtistEmail(artistDto.getEmail());
//        artist.setArtistContact(artistDto.getContact());
//        // 다른 필드들도 필요에 따라 추가
//
//        return artist;
//    }
//
//    private ArtistDto convertToDto(Artist artist) {
//        ArtistDto artistDto = new ArtistDto();
//        artistDto.setId(artist.getArtistId());
//        artistDto.setName(artist.getArtistName());
//        artistDto.setNickName(artist.getArtistNickname());
//        artistDto.setEmail(artist.getArtistEmail());
//        artistDto.setContact(artist.getArtistContact());
//
//        return artistDto;
//    }
//}


import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
import ArtBridge.ArtBridgelogin.service.ArtistService;
import ArtBridge.ArtBridgelogin.service.errorMessage.MyDataAccessException;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceTest {

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistService artistService;

    @Test
    public void createArtist_Success() {
        // given
        ArtistDto artistDto = new ArtistDto();
        artistDto.setId("testId");
        artistDto.setName("Test Artist");

        Artist artist = new Artist();
        artist.setArtistId("testId");
        artist.setArtistName("Test Artist");

        when(artistRepository.findById("testId")).thenReturn(Optional.empty());
        when(artistRepository.create(any(Artist.class))).thenReturn(artist);

        // when
        ArtistDto createdArtist = artistService.createArtist(artistDto);

        // then
        assertNotNull(createdArtist);
        assertEquals("testId", createdArtist.getId());
        assertEquals("Test Artist", createdArtist.getName());
    }

    @Test
    public void createArtist_DuplicateId_ThrowsException() {
        // given
        ArtistDto artistDto = new ArtistDto();
        artistDto.setId("testId");
        artistDto.setName("Test Artist");

        when(artistRepository.findById("testId")).thenReturn(Optional.of(new Artist()));

        // when & then
        assertThrows(IllegalStateException.class, () -> {
            artistService.createArtist(artistDto);
        });
    }

    @Test
    public void readAllArtists_Success() {
        // given
        Artist artist = new Artist();
        artist.setArtistId("testId");
        artist.setArtistName("Test Artist");

        when(artistRepository.readAll()).thenReturn(Collections.singletonList(artist));

        // when
        List<ArtistDto> artistDtos = artistService.readAllArtists();

        // then
        assertNotNull(artistDtos);
        assertEquals(1, artistDtos.size());

        ArtistDto retrievedArtist = artistDtos.get(0);
        assertEquals("testId", retrievedArtist.getId());
        assertEquals("Test Artist", retrievedArtist.getName());
    }

    @Test
    public void readOne_Success() {
        // given
        Artist artist = new Artist();
        artist.setArtistId("testId");
        artist.setArtistName("Test Artist");

        when(artistRepository.readArtistById("testId")).thenReturn(artist);

        // when
        ArtistDto retrievedArtist = artistService.readOne("testId");

        // then
        assertNotNull(retrievedArtist);
        assertEquals("testId", retrievedArtist.getId());
        assertEquals("Test Artist", retrievedArtist.getName());
    }

    @Test
    public void readOne_NotFound_ThrowsException() {
        // given
        when(artistRepository.readArtistById("nonExistentId")).thenReturn(null);

        // when & then
        assertThrows(NoDataFoundException.class, () -> {
            artistService.readOne("nonExistentId");
        });
    }

    @Test
    public void updateArtist_Success() {
        // given
        ArtistDto updatedArtistDto = new ArtistDto();
        updatedArtistDto.setId("testId");
        updatedArtistDto.setName("Updated Artist");

        Artist existingArtist = new Artist();
        existingArtist.setArtistId("testId");
        existingArtist.setArtistName("Test Artist");

        when(artistRepository.findById("testId")).thenReturn(Optional.of(existingArtist));
        when(artistRepository.updateArtist("testId", existingArtist)).thenReturn(existingArtist);

        // when
        ArtistDto updatedArtist = artistService.updateArtist("testId", updatedArtistDto);

        // then
        assertNotNull(updatedArtist);
        assertEquals("testId", updatedArtist.getId());
        assertEquals("Updated Artist", updatedArtist.getName());
    }

    @Test
    public void updateArtist_NotFound_ThrowsException() {
        // given
        ArtistDto updatedArtistDto = new ArtistDto();
        updatedArtistDto.setId("nonExistentId");
        updatedArtistDto.setName("Updated Artist");

        when(artistRepository.findById("nonExistentId")).thenReturn(Optional.empty());

        // when & then
        assertThrows(NoDataFoundException.class, () -> {
            artistService.updateArtist("nonExistentId", updatedArtistDto);
        });
    }
}

