package ArtBridge.ArtBridgelogin.Controller;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class MemberLoginForm {

    private String id;
    private String pw;
    private boolean isArtist;
}
