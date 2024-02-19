package ArtBridge.ArtBridgelogin.controller.dto.artist;

import ArtBridge.ArtBridgelogin.domain.ArtistMention;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class    ArtistMentionCommentDto {

    private String content;

    private Long artistMentionSeq;

    private String memberId;


}
