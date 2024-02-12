package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistDto;
import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
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
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    // CREATE
    @Transactional
    public ArtistDto createArtist(ArtistDto artistDto) {
        try {
            // 아티스트 ID를 통해 이미 존재하는 아티스트인지 확인
            String artistId = artistDto.getId();
            if (artistRepository.findById(artistId).isPresent()) {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }
            // ArtistDto를 Entity로 변환
            Artist artist = convertToEntity(artistDto);

            // 생성된 Artist를 저장하고 반환
            return convertToDto(artistRepository.create(artist));
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to create artist", e);
        }
    }

    // READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<ArtistDto> readAllArtists() {
        try {
            List<Artist> artists = artistRepository.readAll();

            if (artists.isEmpty()) {
                throw new NoDataFoundException("No artists found");
            }

            // 여기서 실제로 DTO로 변환하는 로직을 추가할 수 있습니다.
            return convertToDtoList(artists);
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to read all artists", e);
        }
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public ArtistDto readOne(String id) {
        Artist artist = artistRepository.readArtistById(id);

        if (artist == null) {
            throw new NoDataFoundException("Artist not found with ID: " + id);
        }

        return convertToDto(artist);
    }

    @Transactional
    public boolean login(String userId, String password) {
        try {
            // 로그인 처리 로직
            Artist foundArtist = artistRepository.readArtistById(userId);

            if (foundArtist != null && foundArtist.getArtistPwd().equals(password)) {
                // 로그인 성공 시 true 반환
                return true;
            }
            // 로그인 실패 시 false 반환
            return false;
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to login", e);
        }
    }

    // UPDATE
    @Transactional
    public ArtistDto updateArtist(String artistId, ArtistDto updatedArtistDto) {
        try {
            Artist artist = artistRepository.findById(artistId)
                    .orElseThrow(() -> new NoDataFoundException("등록된 작가가 없습니다."));

            // 필드 설정
            artist.setArtistName(updatedArtistDto.getName());
            artist.setArtistPwd(updatedArtistDto.getPw());
            artist.setArtistNickname(updatedArtistDto.getNickName());
            artist.setArtistEmail(updatedArtistDto.getEmail());
            artist.setArtistContact(updatedArtistDto.getContact());
            // 다른 필드들도 필요에 따라 추가

            // 업데이트 수행
            artistRepository.updateArtist(artistId, artist);

            // 업데이트된 Artist를 반환
            return convertToDto(artist);
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to update artist", e);
        }
    }

    // DELETE
    @Transactional
    public boolean deleteArtist(String id) {
        try {
            // readOne 메서드에서 예외가 발생하면 null을 반환하므로,
            // 예외가 발생하지 않으면 해당 아티스트가 존재하는 것으로 판단
            ArtistDto artist = readOne(id);
            if (artist != null) {
                artistRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (DataAccessException e) {
            // 데이터베이스 예외가 발생한 경우 처리
            throw new MyDataAccessException("Failed to delete artist", e);
        }
    }

    // Function
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
        // 다른 필드들도 필요에 따라 추가

        return artistDto;
    }

    private List<ArtistDto> convertToDtoList(List<Artist> artists) {
        // Artist 리스트를 ArtistDto 리스트로 변환하는 로직
        // 각각의 Artist를 위에서 정의한 convertToDto 메서드를 활용하여 변환
        return artists.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
