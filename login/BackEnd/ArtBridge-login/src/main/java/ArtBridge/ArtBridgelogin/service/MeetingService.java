package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.domain.OpenVidu.Meeting;
import ArtBridge.ArtBridgelogin.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;

    @Transactional
    public Meeting createMeeting(Meeting meeting) {
        return meetingRepository.create(meeting);
    }

    @Transactional(readOnly = true)
    public Meeting findMeetingBySeq(int meetingSeq) {
        return meetingRepository.findMeetingBySeq(meetingSeq);
    }

    @Transactional(readOnly = true)
    public Meeting findMeetingBySession(String sessionId) {
        return meetingRepository.findMeetingBySession(sessionId);
    }

    @Transactional
    public void updateMeetingBySeq(int meetingSeq, String newSessionId) {meetingRepository.updateBySeq(meetingSeq, newSessionId);}

    @Transactional
    public void deleteMeetingBySession(String sessionId) {
        meetingRepository.deleteMeetingBySession(sessionId);
    }
}
