package ArtBridge.ArtBridgelogin.webRTC;

import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.domain.MemberAuctionBidding;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WebrtcService {

    @Autowired
    private WebrtcRepository webrtcRepository;


    public String createBid(Long seq, AuctionPointDetail bidRequest) {
        try {
            webrtcRepository.createBid(seq, bidRequest);
            return "sucess";
        }
        catch (Exception e){
            return "error";
        }
    }

    public MemberDto readWinner(Integer seq) {
        Member member = webrtcRepository.readWinner(seq);
        return convertToDto(member);
    }

    public double readCurrentPrice(Integer seq) {
        try {
            AuctionPointDetail auctionPointDetail = webrtcRepository.readCurrentPrice(seq);
            return auctionPointDetail.getAuctionPointDetailPoint();
        }
        catch (Exception e){
            return 0;
        }
    }
    public String updateAuctionDetails(Integer seq, AuctionPointDetail bidRequest) {
        try {
            //webrtcRepository.updateAuctionDetails(seq, bidRequest);
            return "sucess";
        }
        catch (Exception e){
            return "error";
        }
    }
    public MemberDto readAuctionDetails(Integer seq) {
        Member member = webrtcRepository.readAuctionDetails(seq);
        return convertToDto(member);
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



}
