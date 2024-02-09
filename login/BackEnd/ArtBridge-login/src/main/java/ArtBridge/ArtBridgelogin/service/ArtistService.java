package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
import ArtBridge.ArtBridgelogin.service.errorMessage.MyDataAccessException;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try {
            if (artistRepository.readArtistById(artist.getArtistId()) != null) {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }
            return artistRepository.create(artist);
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to create artist", e);
        }
    }

    //TODO: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Artist> readAllArtists() {
        try {
            List<Artist> artists = artistRepository.readAll();

            if (artists.isEmpty()) {
                throw new NoDataFoundException("No artists found");
            }
            return artists;
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to read all artists", e);
        }
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Artist readOne(String id) {
        try {
            Artist artist = artistRepository.readArtistById(id);

            if (artist == null) {
                throw new NoDataFoundException("Artist not found with ID: " + id);
            }
            return artist;
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to read artist", e);
        }
    }

    @Transactional
    public boolean login(@RequestParam("id") String userId, @RequestParam("pw") String password) {
        try {
            // 로그인 처리 로직
            Artist foundArtist = artistRepository.readArtistById(userId);

            if (foundArtist != null && foundArtist.getArtistPwd().equals(password)) {
                // 로그인 성공 시 true 반환
                return true;
            }
            // 로그인 실패 시 false 반환
            return false;
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to login", e);
        }
    }



    //TODO: UPDATE
    @Transactional
    public Artist updateArtist(String id, Artist updatedArtist) {
        try {
            // readOne 메서드에서 예외가 발생하면 null을 반환하므로,
            // 예외가 발생하지 않으면 해당 아티스트가 존재하는 것으로 판단
            if (readOne(id) != null) {
                return artistRepository.updateArtist(id, updatedArtist);
            } else {
                throw new MyDataAccessException("등록된 작가가 없습니다.");
            }
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to update artist", e);
        }
    }


    //TODO: DELETE
    @Transactional
    public boolean deleteArtist(String id) {
        try {
            // readOne 메서드에서 예외가 발생하면 null을 반환하므로,
            // 예외가 발생하지 않으면 해당 아티스트가 존재하는 것으로 판단
            if (readOne(id) != null) {
                artistRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to delete artist", e);
        }
    }
}
