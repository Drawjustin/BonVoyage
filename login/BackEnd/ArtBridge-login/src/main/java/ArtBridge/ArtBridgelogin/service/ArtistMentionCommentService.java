package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistMentionCommentDto;
import ArtBridge.ArtBridgelogin.domain.ArtistMentionComment;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionCommentRepository;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionRepository;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistMentionCommentService {

    private final ArtistMentionCommentRepository artistMentionCommentRepository;

    @Autowired
    private ArtistMentionRepository artistMentionRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<ArtistMentionCommentDto> readAllArtistMentionComments() {
        try {
            List<ArtistMentionComment> artistMentionComments = artistMentionCommentRepository.findAll();
            return convertEntityListToDtoList(artistMentionComments);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to retrieve artist mention comments.", e);
        }
    }

    public ArtistMentionCommentDto readArtistMentionCommentById(Long id) {
        try {
            Optional<ArtistMentionComment> artistMentionCommentOptional = artistMentionCommentRepository.findById(id);
            return artistMentionCommentOptional.map(this::convertEntityToDto).orElse(null);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to retrieve artist mention comment by ID: " + id, e);
        }
    }

    public ArtistMentionCommentDto createArtistMentionComment(ArtistMentionCommentDto commentDto) {
        try {

            System.out.println("check");
            ArtistMentionComment artistMentionComment = new ArtistMentionComment();

            artistMentionComment.setArtistMention(artistMentionRepository.readOne(commentDto.getArtistMentionSeq()));
            artistMentionComment.setMember(memberRepository.readMemberById(commentDto.getMemberId()));
            artistMentionComment.setArtistMentionCommentContent(commentDto.getContent());
            artistMentionComment.setArtistMentionCommentCreatedDate(LocalDateTime.now());
            ArtistMentionComment createdArtistMentionComment = artistMentionCommentRepository.save(artistMentionComment);
            return convertEntityToDto(createdArtistMentionComment);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to create artist mention comment.", e);
        }
    }

    public ArtistMentionCommentDto updateArtistMentionComment(Long id, ArtistMentionCommentDto updatedCommentDto) {
        try {
            Optional<ArtistMentionComment> existingArtistMentionCommentOptional = artistMentionCommentRepository.findById(id);
            return existingArtistMentionCommentOptional.map(existingArtistMentionComment -> {
                existingArtistMentionComment.setArtistMentionCommentContent(updatedCommentDto.getContent());
                artistMentionCommentRepository.save(existingArtistMentionComment); // 이 부분이 수정되었습니다.
                return convertEntityToDto(existingArtistMentionComment);
            }).orElse(null);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to update artist mention comment by ID: " + id, e);
        }
    }

    public boolean deleteArtistMentionComment(Long id) {
        try {
            Optional<ArtistMentionComment> artistMentionCommentOptional = artistMentionCommentRepository.findById(id);
            return artistMentionCommentOptional.map(existingArtistMentionComment -> {
                artistMentionCommentRepository.deleteById(id);
                return true;
            }).orElse(false);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to delete artist mention comment by ID: " + id, e);
        }
    }

    private ArtistMentionCommentDto convertEntityToDto(ArtistMentionComment artistMentionComment) {
        ArtistMentionCommentDto commentDto = new ArtistMentionCommentDto();
        commentDto.setContent(artistMentionComment.getArtistMentionCommentContent());
        // 나머지 필드도 복사해야 함
        return commentDto;
    }

    private List<ArtistMentionCommentDto> convertEntityListToDtoList(List<ArtistMentionComment> artistMentionComments) {
        return artistMentionComments.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private ArtistMentionComment convertDtoToEntity(ArtistMentionCommentDto commentDto) {
        ArtistMentionComment artistMentionComment = new ArtistMentionComment();


        // 나머지 필드도 복사해야 함
        return artistMentionComment;
    }
}
