package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;


    //TODO: CRETE
    @Transactional
    public Artist createArtist(Artist artist) {
        if (artistRepository.readArtistById(artist.getArtistId()) != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        return artistRepository.create(artist);
    }

    //TODO: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Artist> readAllArtists() {
        return artistRepository.readAll();
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Artist readOne(String id) {
        return artistRepository.readArtistById(id);
    }
    @Transactional
    public ResponseEntity<?> login(@RequestParam("id") String userId, @RequestParam("pw") String password) {
        // 로그인 처리 로직
        Artist foundArtist = artistRepository.readArtistById(userId);

        if (foundArtist != null && foundArtist.getArtistPwd().equals(password)) {
            // 로그인 성공 시 Member 정보와 함께 응답
            return ResponseEntity.ok(foundArtist);
        } else {
            // 로그인 실패 시 401 상태 코드와 실패 메시지 응답
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("바보 멍텅구리 로그인 실패했잔요");
        }
    }


    //TODO: UPDATE
    @Transactional
    public Artist updateArtist(String id, Artist updatedArtist) {
        return artistRepository.updateArtist(id, updatedArtist);
    }

    //TODO: DELETE
    @Transactional
    public void deleteArtist(String id) {
        artistRepository.deleteById(id);
    }
}
