package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionRepository;
import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistMentionService {


    private final ArtistMentionRepository artistMentionRepository;

    @Transactional(readOnly = true)
    public List<ArtistMention> getAllArtistsMention() {
        return artistMentionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ArtistMention findOne(Long id) {
        return artistMentionRepository.findOne(id);
    }

    @Transactional
    public ArtistMention createArtistMention(ArtistMention artistMention) {
        return artistMentionRepository.create(artistMention);
    }

    @Transactional
    public ArtistMention updateArtistMention(Long id, ArtistMention updatedArtistMention) {
        ArtistMention existingArtistMention = artistMentionRepository.findOne(id);

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

    @Transactional
    public void deleteArtistMention(Long mentionSeq) {
        artistMentionRepository.deleteBySeq(mentionSeq);
    }
}
