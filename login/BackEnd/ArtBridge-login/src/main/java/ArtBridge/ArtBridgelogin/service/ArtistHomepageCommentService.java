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
import java.util.ArrayList;
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
        artistHomepageComment.setArtist(artistRepository.readArtistBySeq(artistHomepageCommentDto.getArtistSeq()));
        artistHomepageComment.setMember(memberRepository.readMemberBySeq(artistHomepageCommentDto.getMemberSeq()));
        artistHomepageComment.setArtistHomepageCommentContentCreatedDate(LocalDateTime.now());
        artistHomepageComment.setArtistHomepageCommentIsdeleted(false);
        artistHomepageComment.setArtistHomepageCommentContent(artistHomepageCommentDto.getArtistHomepageCommentContent());
            ArtistHomepageComment newArtistHomepageComment = artistHomepageCommentRepository.create(artistHomepageComment);

        artistHomepageCommentDto.setArtistHomepageCommentSeq(newArtistHomepageComment.getArtistHomepageCommentSeq());

        return artistHomepageCommentDto;
    }


    public List<ArtistHomepageCommentDto> readAlLHomepageCommentByArtist(Long Seq) {


        return convertToDtoList(artistHomepageCommentRepository.readAlLHomepageCommentByArtist(Seq));

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
            existingArtistHomepageComment.setArtistHomepageCommentContent(updatedArtistHomepageComment.getArtistHomepageCommentContent());
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
        artistHomepageCommentDto.setArtistHomepageCommentContent(artistHomepageComment.getArtistHomepageCommentContent());
        artistHomepageCommentDto.setArtistHomepageCommentSeq(artistHomepageComment.getArtistHomepageCommentSeq());
        artistHomepageCommentDto.setArtistSeq(artistHomepageComment.getArtist().getArtistSeq());
        artistHomepageCommentDto.setMemberSeq(artistHomepageComment.getMember().getMemberSeq());
        return artistHomepageCommentDto;
    }

    public List<ArtistHomepageCommentDto> convertToDtoList(List<ArtistHomepageComment>  artistHomepageCommentList) {
        return artistHomepageCommentList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

//    public ArtistHomepageComment convertToEntity(ArtistHomepageCommentDto artistHomepageCommentDto){
//        ArtistHomepageComment artistHomepageComment = new ArtistHomepageComment();
//
//
//        return
//    }
}