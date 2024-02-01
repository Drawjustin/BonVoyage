package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.ArtistHomepageComment;
import ArtBridge.ArtBridgelogin.domain.ArtistMentionComment;
import ArtBridge.ArtBridgelogin.repository.ArtistHomepageCommentRepository;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistMentionCommentService {


    private final ArtistMentionCommentRepository artistMentionCommentRepository;

    @Transactional(readOnly = true)
    public List<ArtistMentionComment> getAllArtistsMentionComment() {
        return artistMentionCommentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ArtistMentionComment findOne(Long id) {
        return artistMentionCommentRepository.findOne(id);
    }

    @Transactional
    public ArtistMentionComment createArtisMentioneComment(ArtistMentionComment artistMentionComment) {
        return artistMentionCommentRepository.create(artistMentionComment);
    }

    @Transactional
    public ArtistMentionComment updateArtistMentionComment(Long id, ArtistMentionComment updatedArtistMentionComment) {
        ArtistMentionComment existingArtistMentionComment = artistMentionCommentRepository.findOne(id);

        if (existingArtistMentionComment != null) {
            // 업데이트할 정보를 새로운 정보로 설정
            existingArtistMentionComment.setArtistMentionCommentContent(updatedArtistMentionComment.getArtistMentionCommentContent());
            // 저장
            artistMentionCommentRepository.create(existingArtistMentionComment);
            return existingArtistMentionComment;
        } else {
            // 예외 처리 또는 적절한 로직 추가
            return null;
        }
    }

    @Transactional
    public void deleteArtistMentionComment(Long id) {
        artistMentionCommentRepository.deleteById(id);
    }
}
