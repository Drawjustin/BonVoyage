package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistMentionDto;
import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionCommentRepository;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionRepository;
import ArtBridge.ArtBridgelogin.repository.ArtistRepository;
import ArtBridge.ArtBridgelogin.service.errorMessage.MyDataAccessException;
import ArtBridge.ArtBridgelogin.service.errorMessage.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistMentionService {

    @Autowired
    private ArtistMentionRepository artistMentionRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ArtistMentionCommentRepository artistMentionCommentRepository;

    // CREATE
    @Transactional
    public ArtistMentionDto createArtistMention(ArtistMentionDto artistMentionDto) {
        try {
            ArtistMention artistMention = new ArtistMention();
            artistMention.setArtist(artistRepository.readArtistById(artistMentionDto.getArtistId()));
            artistMention.setArtistMentionSubject(artistMentionDto.getSubject());
            artistMention.setArtistMentionContent(artistMentionDto.getContent());
            artistMention.setArtistMentionCreatedDate(LocalDateTime.now());
            artistMention.setArtistMentionComments(artistMentionCommentRepository.findAll());
            return convertToDto(artistMentionRepository.create(artistMention));
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Failed to create artist mention", e);
        }
    }
@Transactional
    public List<ArtistMentionDto> readAllMentionsByArtist(Long Seq) {
        try {
            List<ArtistMention> artistMentions = artistMentionRepository.readAllbyArtist(Seq);

            if (artistMentions.isEmpty()) {
                throw new NoDataFoundException("No artist mentions found");
            }

            return convertToDtoList(artistMentions);
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Failed to read all artist mentions", e);
        }
    }
    // READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<ArtistMentionDto> readAllArtistsMention() {
        try {
            List<ArtistMention> artistMentions = artistMentionRepository.readAll();

            if (artistMentions.isEmpty()) {
                throw new NoDataFoundException("No artist mentions found");
            }

            return convertToDtoList(artistMentions);
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Failed to read all artist mentions", e);
        }
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public ArtistMentionDto readOne(Long id) {

        ArtistMention artistMention = artistMentionRepository.readOne(id);

        if (artistMention == null) {
            throw new NoDataFoundException("ID가 " + id + "인 아티스트 멘션을 찾을 수 없습니다.");
        }

        return convertToDto(artistMention);
    }

    // UPDATE
    @Transactional
    public ArtistMentionDto updateArtistMention(Long id, ArtistMentionDto updatedArtistMentionDto) {

        ArtistMention artistMention = artistMentionRepository.readById(id);

        if (artistMention == null) {
            throw new NoDataFoundException("ID가 " + id + "인 아티스트 멘션을 찾을 수 없습니다.");
        }

//        BeanUtils.copyProperties(updatedArtistMentionDto, artistMention, "artistMentionSeq");
        artistMention.setArtistMentionContent(updatedArtistMentionDto.getContent());
        artistMention.setArtistMentionSubject(updatedArtistMentionDto.getSubject());
        artistMentionRepository.updateArtistMention(id, artistMention);

        return convertToDto(artistMention);
    }

    // DELETE
    @Transactional
    public void deleteArtistMention(Long id) {
        try {
            artistMentionRepository.deleteArtistMention(id);
        } catch (DataAccessException e) {
            throw new MyDataAccessException("Failed to delete artist mention", e);
        }
    }

    // Function
    private ArtistMention convertToEntity(ArtistMentionDto artistMentionDto) {
        ArtistMention artistMention = new ArtistMention();
        BeanUtils.copyProperties(artistMentionDto, artistMention);
        return artistMention;
    }

    private ArtistMentionDto convertToDto(ArtistMention artistMention) {
        ArtistMentionDto artistMentionDto = new ArtistMentionDto();
        artistMentionDto.setArtistMentionSeq(artistMention.getArtistMentionSeq());
        artistMentionDto.setArtistId(artistMention.getArtist().getArtistId());
        artistMentionDto.setContent(artistMention.getArtistMentionContent());
        artistMentionDto.setSubject(artistMention.getArtistMentionSubject());
        artistMentionDto.setCreatedDate(artistMention.getArtistMentionCreatedDate());
        System.out.println(artistMentionDto.getContent());
        return artistMentionDto;
    }

    private List<ArtistMentionDto> convertToDtoList(List<ArtistMention> artistMentions) {
        return artistMentions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
