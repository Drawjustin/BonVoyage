package ArtBridge.ArtBridgelogin.test;

import ArtBridge.ArtBridgelogin.domain.ArtistHomepageComment;
import ArtBridge.ArtBridgelogin.repository.ArtistHomepageCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistHomepageCommentService {
    @Autowired
    private ArtistHomepageCommentRepository artistHomepageCommentRepository;

    //TODO: CRETE
    @Transactional
    public ArtistHomepageComment createArtistHomepageComment(ArtistHomepageComment artistHomepageComment) {
        return artistHomepageCommentRepository.create(artistHomepageComment);
    }


    //TODO: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public ArtistHomepageComment readOne(Long seq) {
        return artistHomepageCommentRepository.readOne(seq);
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<ArtistHomepageComment> readAllArtistsHomepageComment() {
        return artistHomepageCommentRepository.readAll();
    }


    //TODO: UPDATE
    @Transactional
    public ArtistHomepageComment updateArtistHomepageComment(Long seq, ArtistHomepageComment updatedArtistHomepageComment) {
        ArtistHomepageComment existingArtistHomepageComment = artistHomepageCommentRepository.readOne(seq);

        if (existingArtistHomepageComment != null) {
            existingArtistHomepageComment.setArtistHompageCommentContent(updatedArtistHomepageComment.getArtistHompageCommentContent());
            artistHomepageCommentRepository.updateArtistHomepageComment(seq, existingArtistHomepageComment);
            return existingArtistHomepageComment;

        } else {
            return null;
        }
    }


    //TODO: DELETE
    @Transactional
    public void deleteArtistHomepageComment(Long seq) {
        artistHomepageCommentRepository.deleteBySeq(seq);
    }
}
