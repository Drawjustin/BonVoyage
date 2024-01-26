package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.member.Member;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMemberSeq();
    }

    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getMemberName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재한다 이자슥아");
        }
    }

    public List<Member> findMembers() {return memberRepository.findAll();}

    public Member findOne(Long memberId) {return memberRepository.findOne(memberId);}

    @Transactional
    public void update(Long id, String name){
        Member member = memberRepository.findOne(id);
        member.setMemberId(name);
    }
}
