package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Transactional(readOnly = true)
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Artist findOne(String id) {
        return artistRepository.findArtistById(id);
    }

    @Transactional
    public Artist createArtist(Artist artist) {
        if (artistRepository.findArtistById(artist.getArtistId()) != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        return artistRepository.create(artist);
    }

    //@PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@RequestParam("id") String userId, @RequestParam("pw") String password) {
        // 로그인 처리 로직

        Artist foundArtist = artistRepository.findArtistById(userId);

        if (foundArtist != null && foundArtist.getArtistPwd().equals(password)) {
            // 로그인 성공 시 Member 정보와 함께 응답
            return ResponseEntity.ok(foundArtist);
        } else {
            // 로그인 실패 시 401 상태 코드와 실패 메시지 응답
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("바보 멍텅구리 로그인 실패했잔요");
        }
    }

    @Transactional
    public Artist updateArtist(String id, Artist updatedArtist) {
        return artistRepository.updateArtist(id, updatedArtist);
    }

    @Transactional
    public void deleteArtist(String id) {
        artistRepository.deleteById(id);
    }


}
