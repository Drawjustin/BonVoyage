package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistHomepageCommentDto;
import ArtBridge.ArtBridgelogin.domain.ArtistHomepageComment;
import ArtBridge.ArtBridgelogin.repository.ArtistHomepageCommentRepository;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistHomepageCommentService {
    @Autowired
    private ArtistHomepageCommentRepository artistHomepageCommentRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private MemberRepository memberRepository;
    //TODO: CRETE
    @Transactional
    public ArtistHomepageCommentDto createArtistHomepageComment(ArtistHomepageCommentDto artistHomepageCommentDto) {

        ArtistHomepageComment artistHomepageComment = new ArtistHomepageComment();

        artistHomepageComment.setArtist(artistRepository.readArtistById(artistHomepageCommentDto.getArtistId()));
        artistHomepageComment.setMember(memberRepository.readMemberById(artistHomepageCommentDto.getMemberId()));

        artistHomepageComment.setArtistHompageCommentContentCreatedDate(LocalDateTime.now());
        //artist_homepage_comment_isdeleted
        //artist_homepage_comment_deleted_date

        artistHomepageComment.setArtistHompageCommentContent(artistHomepageCommentDto.getArtistHompageCommentContent());

        ArtistHomepageComment newArtistHomepageComment = artistHomepageCommentRepository.create(artistHomepageComment);

        artistHomepageCommentDto.setArtistHomepageCommentSeq(newArtistHomepageComment.getArtistHomepageCommentSeq());

        System.out.println(artistHomepageCommentDto.toString());

        return artistHomepageCommentDto;
    }


    //TODO: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public ArtistHomepageCommentDto readOne(Long seq) {
        return convertToDto(artistHomepageCommentRepository.readOne(seq));
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<ArtistHomepageCommentDto> readAllArtistsHomepageComment() {
        List<ArtistHomepageComment> artistHomepageCommentList = artistHomepageCommentRepository.readAll();
        List<ArtistHomepageCommentDto> artistHomepageCommentDtoList = convertToDtoList(artistHomepageCommentList);
        return artistHomepageCommentDtoList;
    }


    //TODO: UPDATE
    @Transactional
    public ArtistHomepageCommentDto updateArtistHomepageComment(Long seq, ArtistHomepageCommentDto updatedArtistHomepageComment) {
        ArtistHomepageComment existingArtistHomepageComment = artistHomepageCommentRepository.readOne(seq);

        if (existingArtistHomepageComment != null) {
            existingArtistHomepageComment.setArtistHompageCommentContent(updatedArtistHomepageComment.getArtistHompageCommentContent());
            artistHomepageCommentRepository.updateArtistHomepageComment(seq, existingArtistHomepageComment);
            return convertToDto(existingArtistHomepageComment);

        } else {
            return null;
        }
    }


    //TODO: DELETE
    @Transactional
    public void deleteArtistHomepageComment(Long seq) {
        artistHomepageCommentRepository.deleteBySeq(seq);
    }

    public ArtistHomepageCommentDto convertToDto(ArtistHomepageComment artistHomepageComment){
        ArtistHomepageCommentDto artistHomepageCommentDto = new ArtistHomepageCommentDto();
        artistHomepageCommentDto.setArtistHompageCommentContent(artistHomepageComment.getArtistHompageCommentContent());
        artistHomepageCommentDto.setArtistHomepageCommentSeq(artistHomepageComment.getArtistHomepageCommentSeq());

        return artistHomepageCommentDto;
    }

    public List<ArtistHomepageCommentDto> convertToDtoList(List<ArtistHomepageComment>  artistHomepageCommentList) {
        return artistHomepageCommentList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}