package ArtBridge.ArtBridgelogin.webRTC;

import ArtBridge.ArtBridgelogin.controller.dto.member.MemberDto;
import ArtBridge.ArtBridgelogin.controller.dto.webRTC.AuctionPointDetailDto;
import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.AuctionPointDetail;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.repository.AuctionRepository;
import ArtBridge.ArtBridgelogin.repository.MemberRepository;
import ArtBridge.ArtBridgelogin.service.errorMessage.MyDataAccessException;
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

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Transactional
    public AuctionPointDetailDto createBid(AuctionPointDetailDto bidRequestDto) {
        try {
            AuctionPointDetail bidRequest = new AuctionPointDetail();

            Auction auction = auctionRepository.readOne(bidRequestDto.getAuctionSeq());
            Member member = memberRepository.readMemberBySeq(bidRequestDto.getMemberSeq());
            bidRequest.setAuction(auction);
            bidRequest.setMember(member);

            bidRequest.setAuctionPointDate(bidRequestDto.getAuctionPointDate());
            bidRequest.setAuctionPointDetailSeq(bidRequestDto.getAuctionPointDetailSeq());
            bidRequest.setAuctionPointDetailIsWin(bidRequestDto.getAuctionPointDetailIsWin());
            bidRequest.setAuctionPointDetailPoint(bidRequestDto.getAuctionPointDetailPoint());
            return convertToDto(webrtcRepository.createBid(bidRequest));
        }
        catch (Exception e){
            throw new MyDataAccessException("Failed to create bid", e);
        }
    }

    @Transactional
    public List<AuctionPointDetailDto> readBidListByAuctionSeq(int seq) {
        return convertToDtoList(webrtcRepository.readBidListByAuctionSeq(seq));
    }

    @Transactional
    public MemberDto readWinner(Integer seq) {

        webrtcRepository.updateAuctionDetails(seq);
        
        Member member = webrtcRepository.readWinner(seq);
        System.out.println(convertToDto(member).toString());
        return convertToDto(member);
    }
    @Transactional
    public double readCurrentPrice(Integer seq) {
        try {
            return webrtcRepository.readCurrentPrice(seq).getAuctionPointDetailPoint();
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



    private AuctionPointDetail convertToEntity(AuctionPointDetailDto auctionPointDetailDto){
        AuctionPointDetail auctionPointDetail = new AuctionPointDetail();
        auctionPointDetail.setAuction(auctionRepository.readOne(auctionPointDetailDto.getAuctionSeq()));
        auctionPointDetail.setMember(memberRepository.readMemberBySeq(auctionPointDetailDto.getMemberSeq()));
        auctionPointDetail.setAuctionPointDate(auctionPointDetailDto.getAuctionPointDate());
        auctionPointDetail.setAuctionPointDetailSeq(auctionPointDetailDto.getAuctionPointDetailSeq());
        auctionPointDetail.setAuctionPointDetailPoint(auctionPointDetailDto.getAuctionPointDetailPoint());
        auctionPointDetail.setAuctionPointDetailIsWin(auctionPointDetailDto.getAuctionPointDetailIsWin());

        return auctionPointDetail;
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
        auctionPointDetailDto.setAuctionSeq(auctionPointDetail.getAuction().getAuctionSeq());
        auctionPointDetailDto.setMemberSeq(auctionPointDetail.getMember().getMemberSeq());
        return auctionPointDetailDto;
    }


    private List<AuctionPointDetailDto> convertToDtoList(List<AuctionPointDetail> details) {
        return details.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
