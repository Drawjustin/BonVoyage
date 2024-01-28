package ArtBridge.ArtBridgelogin.Controller;

import lombok.Data;
import lombok.NonNull;
import org.apache.logging.log4j.message.Message;

@Data
public class MemberForm {

    private String id;
    private String name;
    private String pw;
    private String chekedpw;
    private String nickname;
    private String email;
    private String phonenumber;

}
