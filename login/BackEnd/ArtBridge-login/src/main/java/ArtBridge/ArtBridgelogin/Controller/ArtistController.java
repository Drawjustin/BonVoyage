package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.Controller.form.ArtistLoginForm;
import ArtBridge.ArtBridgelogin.Controller.form.MemberLoginForm;
import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.domain.Item;
import ArtBridge.ArtBridgelogin.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private MemberLoginForm memberForm;

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody ArtistLoginForm artistLoginForm)
    {return artistService.login(artistLoginForm.getId(),artistLoginForm.getPw());}

    // 로그인 정보를 받기 위한 간단한 DTO 클래스


    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable String id)
    {return artistService.findOne(id);}

    @PostMapping("/new")
    public Artist createArtist(@RequestBody Artist artist)
    {return artistService.createArtist(artist);}

    @PutMapping("/{id}")
    public Artist updateArtist(@PathVariable String id, @RequestBody Artist updatedArtist)
    {return artistService.updateArtist(id, updatedArtist);}

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable String id)
    {artistService.deleteArtist(id);}


}
