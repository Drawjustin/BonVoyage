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
    public ArtistHomepageComment findOne(Long seq) {
        return artistHomepageCommentRepository.findOne(seq);
    }

    @Transactional
    public ArtistHomepageComment createArtistHomepageComment(ArtistHomepageComment artistHomepageComment) {
        return artistHomepageCommentRepository.create(artistHomepageComment);
    }

    @Transactional
    public ArtistHomepageComment updateArtistHomepageComment(Long seq, ArtistHomepageComment updatedArtistHomepageComment) {
        ArtistHomepageComment existingArtistHomepageComment = artistHomepageCommentRepository.findOne(seq);

        if (existingArtistHomepageComment != null) {
            existingArtistHomepageComment.setArtistHompageCommentContent(updatedArtistHomepageComment.getArtistHompageCommentContent());
            artistHomepageCommentRepository.updateArtistHomepageComment(seq, existingArtistHomepageComment);
            return existingArtistHomepageComment;
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteArtistHomepageComment(Long seq) {
        artistHomepageCommentRepository.deleteBySeq(seq);
    }

}
