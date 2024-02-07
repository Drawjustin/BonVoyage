package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.repository.ArtistMentionRepository;
import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistMentionService {


    private final ArtistMentionRepository artistMentionRepository;


    //TODO: CRETE
    @Transactional
    public ArtistMention createArtistMention(ArtistMention artistMention) {
        return artistMentionRepository.create(artistMention);
    }


    //TODO: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public ArtistMention readOne(Long id) {
        return artistMentionRepository.readOne(id);
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<ArtistMention> readAllArtistsMention() {
        return artistMentionRepository.readAll();
    }


    //TODO: UPDATE
    @Transactional
    public ArtistMention updateArtistMention(Long id, ArtistMention updatedArtistMention) {
        ArtistMention existingArtistMention = artistMentionRepository.readOne(id);

        if (existingArtistMention != null) {
            // 업데이트할 정보를 새로운 정보로 설정
            existingArtistMention.setArtistMentionContent(updatedArtistMention.getArtistMentionContent());
            existingArtistMention.setArtistMentionSubject(updatedArtistMention.getArtistMentionSubject());
            // 저장
            artistMentionRepository.create(existingArtistMention);
            return existingArtistMention;
        } else {
            // 예외 처리 또는 적절한 로직 추가
            return null;
        }
    }

    //TODO: DELETE
    @Transactional
    public void deleteArtistMention(Long mentionSeq) {
        artistMentionRepository.deleteBySeq(mentionSeq);
    }
}
