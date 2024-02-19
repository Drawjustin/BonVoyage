package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistMentionCommentDto;
import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistMentionDto;
import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import ArtBridge.ArtBridgelogin.domain.ArtistMentionComment;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionCommentRepository;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionRepository;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private MemberService memberService;

    @Autowired
    private ArtistMentionService artistMentionService;

    // ArtistMention id를 통해 아래에 달려있는 ArtistMentionComment 리스트 조회
    public List<ArtistMentionCommentDto> readArtistMentionCommentByMentionId(Long id) {
        try {
            List<ArtistMentionComment> artistMentionComments = artistMentionCommentRepository.findByMentionId(id);
            return convertEntityListToDtoList(artistMentionComments);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve artist mention comments.", e);
        }
    }

    // ArtistMentionComment id를 통해 단건 댓글 조회
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

            ArtistMentionComment artistMentionComment = new ArtistMentionComment();

            artistMentionComment.setArtistMentionCommentContent(commentDto.getContent());
            // artistMentionCommentIsdeleted;
            artistMentionComment.setArtistMentionCommentCreatedDate(LocalDateTime.now());
            artistMentionComment.setMember(memberRepository.readMemberById(commentDto.getMemberId()));
            artistMentionComment.setArtistMention(null);

//            ArtistMentionComment createdArtistMentionComment = artistMentionCommentRepository.create(artistMentionComment);

            System.out.println("check");

            return commentDto;
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
                artistMentionCommentRepository.create(existingArtistMentionComment);
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
        commentDto.setArtistMentionSeq(artistMentionComment.getArtistMention().getArtistMentionSeq());
        commentDto.setMemberId(artistMentionComment.getMember().getMemberId());

        return commentDto;
    }

    private List<ArtistMentionCommentDto> convertEntityListToDtoList(List<ArtistMentionComment> artistMentionComments) {
        return artistMentionComments.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private ArtistMentionComment convertDtoToEntity(ArtistMentionCommentDto commentDto) {
        ArtistMentionComment artistMentionComment = new ArtistMentionComment();
        if (commentDto.getArtistMentionSeq() != null && commentDto.getMemberId() != null) {
            ArtistMentionDto artistMentionDto = artistMentionService.readOne(commentDto.getArtistMentionSeq());
            MemberDto memberDto = memberService.findMemberById(commentDto.getMemberId());

            ArtistMention artistMention = convertDtoToEntity(artistMentionDto);
            Member member = convertDtoToEntity(memberDto);

            artistMentionComment.setArtistMentionCommentContent(commentDto.getContent());
            artistMentionComment.setArtistMention(artistMention);
            artistMentionComment.setMember(member);
        }
        return artistMentionComment;
    }

    private ArtistMention convertDtoToEntity(ArtistMentionDto artistMentionDto) {
        ArtistMention artistMention = new ArtistMention();
        BeanUtils.copyProperties(artistMentionDto, artistMention);
        return artistMention;
    }

    private Member convertDtoToEntity(MemberDto memberDto) {
        Member member = new Member();
        member.setMemberId(memberDto.getMemberId());
        member.setMemberName(memberDto.getMemberName());
        member.setMemberPwd(memberDto.getMemberPwd());
        member.setMemberNickname(memberDto.getMemberNickname());
        member.setMemberEmail(memberDto.getMemberEmail());
        member.setMemberContact(memberDto.getMemberContact());
        return member;
    }
}
