package ArtBridge.ArtBridgelogin.webRTC;

import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import ArtBridge.ArtBridgelogin.controller.dto.webRTC.AuctionPointDetailDto;
import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.domain.MemberAuctionBidding;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WebrtcService {

    @Autowired
    private WebrtcRepository webrtcRepository;

    @Transactional
    public String createBid(Long seq, AuctionPointDetailDto bidRequestDto) {
        try {
            AuctionPointDetail bidRequest = new AuctionPointDetail();

            Auction auction = new Auction();
            auction.setAuctionSeq(7);
            Member member = new Member();
            member.setMemberSeq(3L);
            bidRequest.setAuction(auction);
            bidRequest.setMember(member);

            bidRequest.setAuctionPointDate(bidRequestDto.getAuctionPointDate());
            bidRequest.setAuctionPointDetailSeq(bidRequestDto.getAuctionPointDetailSeq());
            bidRequest.setAuctionPointDetailIsWin(bidRequestDto.getAuctionPointDetailIsWin());
            bidRequest.setAuctionPointDetailPoint(bidRequestDto.getAuctionPointDetailPoint());
            webrtcRepository.createBid(seq, bidRequest);
            return "sucess";
        }
        catch (Exception e){
            return "error";
        }
    }
    @Transactional
    public MemberDto readWinner(Integer seq) {
        Member member = webrtcRepository.readWinner(seq);
        return convertToDto(member);
    }
    @Transactional
    public double readCurrentPrice(Integer seq) {
        try {
            AuctionPointDetail auctionPointDetail = webrtcRepository.readCurrentPrice(seq);
            return auctionPointDetail.getAuctionPointDetailPoint();
        }
        catch (Exception e){
            return 0;
        }
    }
    @Transactional
    public String updateAuctionDetails(Integer seq) {
        try {
            webrtcRepository.updateAuctionDetails(seq);
            return "sucess";
        }
        catch (Exception e){
            return "error";
        }
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
