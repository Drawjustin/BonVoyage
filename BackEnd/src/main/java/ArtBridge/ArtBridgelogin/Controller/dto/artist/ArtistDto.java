package ArtBridge.ArtBridgelogin.controller.dto.artist;

import lombok.Data;

@Data
public class ArtistDto {

    private Long seq;
    private String name;
    private String id;
    private String pw;
    private String pwCheck;
    private String nickName;
    private String email;
    private String contact;

}
