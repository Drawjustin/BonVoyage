package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Artist findOne(Long id) {
        return artistRepository.findOne(id);
    }

    @Transactional
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Transactional
    public Artist updateArtist(Long id, Artist updatedArtist) {
        Artist existingArtist = artistRepository.findOne(id);

        if (existingArtist != null) {
            // 업데이트할 정보를 새로운 정보로 설정
            existingArtist.setArtistName(updatedArtist.getArtistName());
            existingArtist.setArtistPwd(updatedArtist.getArtistPwd());
            existingArtist.setArtistNickname(updatedArtist.getArtistNickname());
            existingArtist.setArtistEmail(updatedArtist.getArtistEmail());
            existingArtist.setArtistContact(updatedArtist.getArtistContact());
            existingArtist.setArtistPoint(updatedArtist.getArtistPoint());
            existingArtist.setArtistIsdeleted(updatedArtist.isArtistIsdeleted());
            existingArtist.setArtistDeletedDate(updatedArtist.getArtistDeletedDate());
            existingArtist.setArtistCreatedDate(updatedArtist.getArtistCreatedDate());

            // 저장
            artistRepository.save(existingArtist);
            return existingArtist;
        } else {
            // 예외 처리 또는 적절한 로직 추가
            return null;
        }
    }

    @Transactional
    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }
}
