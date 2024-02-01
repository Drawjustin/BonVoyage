package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.ArtistHomepageComment;
import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import ArtBridge.ArtBridgelogin.repository.ArtistHomepageCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistHomepageCommentService {
    @Autowired
    private ArtistHomepageCommentRepository artistHomepageCommentRepository;

    @Transactional(readOnly = true)
    public List<ArtistHomepageComment> getAllArtistsHomepageComment() {
        return artistHomepageCommentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ArtistHomepageComment findOne(Long id) {
        return artistHomepageCommentRepository.findOne(id);
    }

    @Transactional
    public ArtistHomepageComment createArtistHomepageComment(ArtistHomepageComment artistHomepageComment) {
        return artistHomepageCommentRepository.create(artistHomepageComment);
    }

    @Transactional
    public ArtistHomepageComment updateArtistHomepageComment(Long id, ArtistHomepageComment updatedArtistHomepageComment) {
        ArtistHomepageComment existingArtistHomepageComment = artistHomepageCommentRepository.findOne(id);

        if (existingArtistHomepageComment != null) {
            // 업데이트할 정보를 새로운 정보로 설정
            existingArtistHomepageComment.setArtistHompageCommentContent(updatedArtistHomepageComment.getArtistHompageCommentContent());
            // 저장
            artistHomepageCommentRepository.create(existingArtistHomepageComment);
            return existingArtistHomepageComment;
        } else {
            // 예외 처리 또는 적절한 로직 추가
            return null;
        }
    }

    @Transactional
    public void deleteArtistHomepageComment(Long id) {
        artistHomepageCommentRepository.deleteById(id);
    }

}
