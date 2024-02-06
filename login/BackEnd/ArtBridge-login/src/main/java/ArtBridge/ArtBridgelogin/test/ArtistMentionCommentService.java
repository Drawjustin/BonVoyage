package ArtBridge.ArtBridgelogin.test;

import ArtBridge.ArtBridgelogin.domain.ArtistMentionComment;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistMentionCommentService {


    private final ArtistMentionCommentRepository artistMentionCommentRepository;

    //TODO: CRETE
    @Transactional
    public ArtistMentionComment createArtisMentioneComment(ArtistMentionComment artistMentionComment) {
        return artistMentionCommentRepository.create(artistMentionComment);
    }


    //TODO: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<ArtistMentionComment> readAllArtistsMentionComment() {
        return artistMentionCommentRepository.readAll();
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public ArtistMentionComment readOne(Long id) {
        return artistMentionCommentRepository.readOne(id);
    }


    //TODO: UPDATE
    @Transactional
    public ArtistMentionComment updateArtistMentionComment(Long seq, ArtistMentionComment updatedArtistMentionComment) {
        ArtistMentionComment existingArtistMentionComment = artistMentionCommentRepository.readOne(seq);

        if (existingArtistMentionComment != null) {
            // 업데이트할 정보를 새로운 정보로 설정
            existingArtistMentionComment.setArtistMentionCommentContent(updatedArtistMentionComment.getArtistMentionCommentContent());
            artistMentionCommentRepository.updateArtistMentionComment(seq, existingArtistMentionComment);
            return existingArtistMentionComment;
        } else {
            return null;
        }
    }


    //TODO: DELETE
    @Transactional
    public void deleteArtistMentionComment(Long id) {
        artistMentionCommentRepository.deleteById(id);
    }
}
