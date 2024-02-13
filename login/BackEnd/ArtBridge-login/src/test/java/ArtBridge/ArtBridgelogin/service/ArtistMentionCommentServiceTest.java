package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.artist.ArtistMentionCommentDto;
import ArtBridge.ArtBridgelogin.domain.ArtistMentionComment;
import ArtBridge.ArtBridgelogin.repository.ArtistMentionCommentRepository;
import ArtBridge.ArtBridgelogin.service.ArtistMentionCommentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ArtistMentionCommentServiceTest {

    @Mock
    private ArtistMentionCommentRepository artistMentionCommentRepository;

    @InjectMocks
    private ArtistMentionCommentService artistMentionCommentService;

    @Test
    public void testReadAllArtistMentionComments() {
        // 가짜 데이터 생성
        ArtistMentionComment comment1 = new ArtistMentionComment();
        comment1.setArtistMentionCommentContent("Comment 1");

        ArtistMentionComment comment2 = new ArtistMentionComment();
        comment2.setArtistMentionCommentContent("Comment 2");

        List<ArtistMentionComment> fakeData = Arrays.asList(comment1, comment2);

        // Mock 설정
        when(artistMentionCommentRepository.findAll()).thenReturn(fakeData);

        // 테스트 실행
        List<ArtistMentionCommentDto> result = artistMentionCommentService.readAllArtistMentionComments();

        // 결과 확인
        assertEquals(2, result.size());
        assertEquals("Comment 1", result.get(0).getContent());
        assertEquals("Comment 2", result.get(1).getContent());

        // Mock이 제대로 호출되었는지 확인
        verify(artistMentionCommentRepository, times(1)).findAll();
    }

    // 다른 메서드에 대한 유사한 방식으로 테스트 코드 작성 가능
}
