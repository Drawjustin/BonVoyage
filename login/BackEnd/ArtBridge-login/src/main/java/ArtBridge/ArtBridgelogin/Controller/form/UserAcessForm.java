package ArtBridge.ArtBridgelogin.Controller.form;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Member;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserAcessForm {

    private int isArtist;
    private Artist artist;
    private Member member;
    private String token;
}
