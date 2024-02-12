package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Auction;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.repository.AuctionRepository;
import ArtBridge.ArtBridgelogin.repository.ItemRepository;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;

    //Todo: CREATE
    @Transactional
    public Auction createAuction(Auction auction) {
        return auctionRepository.create(auction);
    }


    //Todo: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Auction> readAllAuction(){
        return auctionRepository.readAll();
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Auction readOne(int seq) {
        return auctionRepository.readOne(seq);
    }


    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public ArtistDto readAuctionByArtistSeq(int seq) {
        Item item =  auctionRepository.readItemByAuctionSeq(seq);
        Artist artist = auctionRepository.readArtistByItemSeq(item.getItemSeq());

        if (artist == null) {
            throw new NoDataFoundException("Artist not found with ID: " + item.getItemSeq());
        }

        return convertToDto(artist);
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Item readItemByAuctionSeq(int seq) {
        return auctionRepository.readItemByAuctionSeq(seq);
    }
    @Transactional
    public List<Auction> readAuctionsBySameAuthor(Long authorId) {
        return auctionRepository.readAuctionsBySameAuthor(authorId);
    }


    //Todo: UPDATE
    @Transactional
    public Auction updateAuction(int seq, Auction updatedAuction) {
        auctionRepository.updateAuction(seq, updatedAuction);
        return auctionRepository.readOne(seq);
    }


    //Todo: DELETE
    @Transactional
    public void deleteAuction(int seq) {
        auctionRepository.deleteById(seq);
    }

    private Artist convertToEntity(ArtistDto artistDto) {
        Artist artist = new Artist();
        artist.setArtistId(artistDto.getId());
        artist.setArtistName(artistDto.getName());
        artist.setArtistPwd(artistDto.getPw());
        artist.setArtistNickname(artistDto.getNickName());
        artist.setArtistEmail(artistDto.getEmail());
        artist.setArtistContact(artistDto.getContact());
        // 다른 필드들도 필요에 따라 추가

        return artist;
    }

    private ArtistDto convertToDto(Artist artist) {
        ArtistDto artistDto = new ArtistDto();
        artistDto.setId(artist.getArtistId());
        artistDto.setName(artist.getArtistName());
        artistDto.setNickName(artist.getArtistNickname());
        artistDto.setEmail(artist.getArtistEmail());
        artistDto.setContact(artist.getArtistContact());

        return artistDto;
    }

}
