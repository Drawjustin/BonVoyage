package ArtBridge.ArtBridgelogin.webRTC;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
import ArtBridge.ArtBridgelogin.controller.dto.auction.AuctionDto;
import ArtBridge.ArtBridgelogin.controller.dto.item.ItemDto;
import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import ArtBridge.ArtBridgelogin.controller.dto.webRTC.AuctionPointDetailDto;
import ArtBridge.ArtBridgelogin.domain.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
    public List<AuctionPointDetailDto> readBidListByAuctionSeq(int seq) {
        return convertToDtoList(webrtcRepository.readBidListByAuctionSeq(seq));
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

    private AuctionPointDetailDto convertToDto(AuctionPointDetail auctionPointDetail) {
        AuctionPointDetailDto auctionPointDetailDto = new AuctionPointDetailDto();
        auctionPointDetailDto.setAuctionPointDetailSeq(auctionPointDetail.getAuctionPointDetailSeq());
        auctionPointDetailDto.setAuctionPointDetailPoint(auctionPointDetail.getAuctionPointDetailPoint());
        auctionPointDetailDto.setAuctionPointDetailIsWin(auctionPointDetail.getAuctionPointDetailIsWin());
        auctionPointDetailDto.setAuctionPointDate(auctionPointDetail.getAuctionPointDate());

        // Map MemberDto
        if (auctionPointDetail.getMember() != null) {
            MemberDto memberDto = new MemberDto();
            memberDto.setMemberId(auctionPointDetail.getMember().getMemberId());
            // 이후 필요한 필드들도 여기서 설정해주세요.
            auctionPointDetailDto.setMember(memberDto);
        }

        // Map AuctionDto
        if (auctionPointDetail.getAuction() != null) {
            AuctionDto auctionDto = new AuctionDto();
            auctionDto.setAuctionSeq(auctionPointDetail.getAuction().getAuctionSeq());
            // 이후 필요한 필드들도 여기서 설정해주세요.
            auctionPointDetailDto.setAuction(auctionDto);
        }

        return auctionPointDetailDto;
    }


    private List<AuctionPointDetailDto> convertToDtoList(List<AuctionPointDetail> details) {
        return details.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
