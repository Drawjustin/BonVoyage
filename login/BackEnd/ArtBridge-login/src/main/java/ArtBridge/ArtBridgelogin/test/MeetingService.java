package ArtBridge.ArtBridgelogin.test;

import ArtBridge.ArtBridgelogin.domain.OpenVidu.Meeting;
import ArtBridge.ArtBridgelogin.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MeetingService {

    @Autowired
    private final MeetingRepository meetingRepository;


    //Todo: CREATE
    @Transactional
    public Meeting createMeeting(Meeting meeting) {
        return meetingRepository.create(meeting);
    }


    //Todo: READ
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Meeting readMeetingBySeq(int meetingSeq) {
        return meetingRepository.readMeetingBySeq(meetingSeq);
    }
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public Meeting readMeetingBySession(String sessionId) {
        return meetingRepository.readMeetingBySession(sessionId);
    }


    //Todo: UPDATE
    @Transactional
    public void updateMeetingBySeq(int meetingSeq, String newSessionId) {
        meetingRepository.updateBySeq(meetingSeq, newSessionId);}


    //Todo: DELETE
    @Transactional
    public void deleteMeetingBySession(String sessionId) {
        meetingRepository.deleteMeetingBySession(sessionId);
    }



}
