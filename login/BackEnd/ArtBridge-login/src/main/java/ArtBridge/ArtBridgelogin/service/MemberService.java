package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.member.Member;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Transactional
    public Member updateMember(Long id, Member updatedMember) {
        Member existingMember = memberRepository.findOne(id);

        if (existingMember != null) {
            // 업데이트할 정보를 새로운 정보로 설정
            existingMember.setMemberName(updatedMember.getMemberName());
            existingMember.setMemberPwd(updatedMember.getMemberPwd());
            existingMember.setMemberNickname(updatedMember.getMemberNickname());
            existingMember.setMemberEmail(updatedMember.getMemberEmail());
            existingMember.setMemberContact(updatedMember.getMemberContact());
            existingMember.setMemberPoint(updatedMember.getMemberPoint());
            existingMember.setMemberIsDeleted(updatedMember.isMemberIsDeleted());
            existingMember.setMemberDeletedDate(updatedMember.getMemberDeletedDate());
            existingMember.setMemberCreatedDate(updatedMember.getMemberCreatedDate());

            // 저장
            memberRepository.save(existingMember);
            return existingMember;
        } else {
            // 예외 처리 또는 적절한 로직 추가
            return null;
        }
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

}
