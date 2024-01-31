package ArtBridge.ArtBridgelogin.Controller;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
public class ArtistLoginForm {

    private String id;
    private String pw;
    private boolean isArtist;
}
