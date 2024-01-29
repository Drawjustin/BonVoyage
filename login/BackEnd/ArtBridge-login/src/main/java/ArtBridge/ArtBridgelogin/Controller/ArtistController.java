package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Member;
import ArtBridge.ArtBridgelogin.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private MemberForm memberForm;

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody MemberForm memberForm) {
        // LoginForm은 사용자가 제출한 로그인 정보를 담은 클래스로 가정합니다.
        // 실제로는 사용자 입력을 검증하고, 유효한 경우 로그인 처리를 수행해야 합니다.

        String username = memberForm.getId();
        String password = memberForm.getPw();

        // 여기에서 로그인 처리 로직을 수행하고 성공 여부에 따라 응답을 반환합니다.
        if ("user123".equals(username) && "password123".equals(password)) {
            return "Login successful";
        } else {
            return "Login failed";
        }
    }

    // 로그인 정보를 받기 위한 간단한 DTO 클래스


    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable Long id) {
        return artistService.findOne(id);
    }

    @PostMapping("/new")
    public Artist createArtist(@RequestBody Artist artist) {
        return artistService.createArtist(artist);
    }

    @PutMapping("/{id}")
    public Artist updateArtist(@PathVariable Long id, @RequestBody Artist updatedArtist) {
        return artistService.updateArtist(id, updatedArtist);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
    }
}
