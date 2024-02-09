package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.OpenVidu.Meeting;
import ArtBridge.ArtBridgelogin.service.AuctionService;
import ArtBridge.ArtBridgelogin.service.MeetingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.openvidu.java.client.ConnectionProperties;
import io.openvidu.java.client.Connection;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/openvidu")
@RequiredArgsConstructor
//@Api("OpenVidu Controller API V1")
public class OpenViduController {

    private MeetingService meetingService;
    private AuctionService auctionService;
//    @Autowired
//    public OpenViduController(AuctionService auctionService) {
//        this.auctionService = auctionService;
//    }

    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;

    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;

    private OpenVidu openvidu;

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    /**
     */
    @GetMapping
    public ResponseEntity<?> printAll() {
        System.out.println(OPENVIDU_URL);
        System.out.println(OPENVIDU_SECRET);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param params The Session properties
     * @return The Session ID
     */

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/api/sessions")
//    @ApiOperation(value = "방 생성을 위한 세션ID 생성")
    public ResponseEntity<?> createSession(@RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {

         Map<String, Object> check = new HashMap<>();
//         String customSessionId = (String) params.get("customSessionId");
//         int auctionSeq = (int) params.get("auctionSeq");

// //        logger.info("*** createSession 메소드 호출");
//         SessionProperties properties = SessionProperties.fromJson(params).build();
//         Session session = openvidu.createSession(properties);

//         Meeting meeting = new Meeting();
//         meeting.setMeetingSessionId(session.getSessionId());
//         meeting.setAuction(auctionService.readOne(auctionSeq));

//         try {
// //            meetingService.deleteAllSessionConsultantId(params.read("ConsultantId").toString());
// //            logger.info("*** deleteAllSessionConsultantId 호출");

//             meetingService.createMeeting(meeting);
// //            logger.info("*** createSession 호출");
// //
//             check.put("msg", "success");
//             check.put("sessionId", session.getSessionId());
// //
// //            logger.info("*** createSession 메소드 종료");
// //            logger.info("*** 세션 생성 : " + session.readSessionId());
//             return ResponseEntity.status(HttpStatus.OK).body(check);
//         } catch (Exception e) {

//             return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
//         }
        OpenVidu openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
        SessionProperties properties = new SessionProperties.Builder().build();
        Session session = openVidu.createSession(properties);
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
            .role(OpenViduRole.PUBLISHER)
            .data("Alice")
            .build();
        Connection connection = session.createConnection(connectionProperties);
        String token = connection.getToken(); // Send this string to the client side
        check.put("msg", "success");
        check.put("sessionId", session.getSessionId());
        check.put("token",token);
        return ResponseEntity.status(HttpStatus.OK).body(check);

    }

    /**
     * @param sessionId The Session in which to create the Connection
     * @param params    The Connection properties
     * @return The Token associated to the Connection
     */
    @PostMapping("/api/sessions/{sessionId}/connections")
//    @ApiOperation(value = "세션ID로 생성된 방 입장")
    public ResponseEntity<?> createConnection(@PathVariable("sessionId") String sessionId,
                                              @RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException, JsonProcessingException {

        Map<String, Object> check = new HashMap<>();

//        logger.info("*** createConnection 메소드 호출");

        Session tarreadSession = openvidu.getActiveSession(sessionId);

        if (tarreadSession == null) {
            // 세션이 없다
            check.put("msg", "fail");
//            logger.info("*** createConnection 메소드 오류");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(check);
        } else {
            ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
            Connection connection = tarreadSession.createConnection(properties);

            try {
                Meeting meeting = meetingService.readMeetingBySession(tarreadSession.getSessionId());
//                logger.info("*** readMeeting 호출");

//                meetingService.updateMeeting(meetingDto);

                check.put("msg", "success");
                check.put("sessionId", tarreadSession.getSessionId());
                check.put("token", connection.getToken());

//                    logger.info("*** createConnection 종료");
//                    logger.info("*** sessionId : {}", session.readSessionId());
//                    logger.info("*** token : {}", connection.readToken());
                return ResponseEntity.status(HttpStatus.OK).body(check);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
    }

    @DeleteMapping("/api/sessions/{sessionId}")
//    @ApiOperation(value = "세션ID로 생성된 방 삭제")
    public ResponseEntity<?> deleteSession(@PathVariable("sessionId") String sessionId) {

        Map<String, Object> check = new HashMap<>();

//        logger.info("*** deleteSession - 호출");

        try {
            meetingService.deleteMeetingBySession(sessionId);

//            check.put("msg", "success");

            return ResponseEntity.status(HttpStatus.OK).body(check);
        } catch (Exception e) {
//            check.put("msg", "fail");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(check);
        }

    }

}
