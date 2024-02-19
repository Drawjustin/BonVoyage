package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.LoginReturnForm;
import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import ArtBridge.ArtBridgelogin.service.errorMessage.MyDataAccessException;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // CREATE
    @Transactional
    public MemberDto createMember(MemberDto memberDto) {
        try {
            // 아이디를 통해 이미 존재하는 회원인지 확인
            String memberId = memberDto.getMemberId();
            if (memberRepository.findByMemberId(memberId).isPresent()) {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }

            // MemberDto를 Member 엔티티로 변환
            Member member = convertToEntity(memberDto);

            // 생성된 Member를 저장하고 변환
            return convertToDto(memberRepository.create(member));
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to create member", e);
        }
    }

    // READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<MemberDto> readAllMembers() {
        try {
            List<Member> members = memberRepository.readAll();

            if (members.isEmpty()) {
                throw new NoDataFoundException("No members found");
            }

            // DTO로 변환
            return convertToDtoList(members);
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to read all members", e);
        }
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public MemberDto findMemberById(String memberId) {
        try {
            Member member = memberRepository.findByMemberId(memberId)
                    .orElseThrow(() -> new NoDataFoundException("Member not found with ID: " + memberId));

            return convertToDto(member);
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Failed to find member by ID", e);
        }
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public MemberDto readOne(String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NoDataFoundException("Member not found with ID: " + memberId));

        return convertToDto(member);
    }

    @Transactional
    public LoginReturnForm login(String memberId, String password) {
        try {
            // 로그인 처리 로직
            Member foundMember = memberRepository.findByMemberIdAndMemberPwd(memberId, password)
                    .orElse(null);

            LoginReturnForm loginReturnForm = new LoginReturnForm();
            loginReturnForm.setSeq(foundMember.getMemberSeq());
            loginReturnForm.setId(foundMember.getMemberId());

            return loginReturnForm;
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to login", e);
        }
    }

    // UPDATE
    @Transactional
    public MemberDto updateMember(String memberId, MemberDto updatedMemberDto) {
        try {
            Member member = memberRepository.findByMemberId(memberId)
                    .orElseThrow(() -> new NoDataFoundException("등록된 회원이 없습니다."));

            // 필드 설정
            member.setMemberName(updatedMemberDto.getMemberName());
            member.setMemberPwd(updatedMemberDto.getMemberPwd());
            member.setMemberNickname(updatedMemberDto.getMemberNickname());
            member.setMemberEmail(updatedMemberDto.getMemberEmail());
            member.setMemberContact(updatedMemberDto.getMemberContact());
            // 다른 필드들도 필요에 따라 추가

            // 업데이트 수행
            memberRepository.updateMember(memberId, member);

            // 업데이트된 Member를 반환
            return convertToDto(member);
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to update member", e);
        }
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Member readMemberById(String memberId) {

        Member findMember = memberRepository.readMemberById(memberId);

        if (findMember == null) {
            throw new NoDataFoundException("ArtistSeq not found for artist with ID: " + memberId);
        }


        return findMember;
    }

    // DELETE
    @Transactional
    public boolean deleteMember(String memberId) {
        try {
            // readOne 메서드에서 예외가 발생하면 null을 반환하므로,
            // 예외가 발생하지 않으면 해당 회원이 존재하는 것으로 판단
            MemberDto member = readOne(memberId);
            if (member != null) {
                memberRepository.deleteByMemberId(memberId);
                return true;
            }
            return false;
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to delete member", e);
        }
    }

    // Function
    private Member convertToEntity(MemberDto memberDto) {
        Member member = new Member();
        member.setMemberId(memberDto.getMemberId());
        member.setMemberName(memberDto.getMemberName());
        member.setMemberPwd(memberDto.getMemberPwd());
        member.setMemberNickname(memberDto.getMemberNickname());
        member.setMemberEmail(memberDto.getMemberEmail());
        member.setMemberContact(memberDto.getMemberContact());
        member.setMemberCreatedDate(memberDto.getMemberCreatedDate());
        member.setMemberPoint(memberDto.getMemberPoint());

        return member;
    }

    private MemberDto convertToDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberSeq(member.getMemberSeq());
        memberDto.setMemberId(member.getMemberId());
        memberDto.setMemberName(member.getMemberName());
        memberDto.setMemberPwd(member.getMemberPwd());
        memberDto.setMemberNickname(member.getMemberNickname());
        memberDto.setMemberEmail(member.getMemberEmail());
        memberDto.setMemberContact(member.getMemberContact());
        memberDto.setMemberPoint(member.getMemberPoint());
        memberDto.setMemberIsDeleted(member.isMemberIsDeleted());
        memberDto.setMemberDeletedDate(member.getMemberDeletedDate());
        memberDto.setMemberCreatedDate(member.getMemberCreatedDate());

        return memberDto;
    }

    private List<MemberDto> convertToDtoList(List<Member> members) {
        // Member 리스트를 MemberDto 리스트로 변환하는 로직
        // 각각의 Member를 위에서 정의한 convertToDto 메서드를 활용하여 변환
        return members.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
