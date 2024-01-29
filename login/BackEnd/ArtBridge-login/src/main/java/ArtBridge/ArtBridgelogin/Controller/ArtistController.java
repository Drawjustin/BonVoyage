package ArtBridge.ArtBridgelogin.Controller;

import ArtBridge.ArtBridgelogin.domain.Artist;
import ArtBridge.ArtBridgelogin.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable Long id) {
        return artistService.findOne(id);
    }

    @PostMapping
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
