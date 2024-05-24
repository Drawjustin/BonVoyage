package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.controller.dto.webRTC.PointDto;
import ArtBridge.ArtBridgelogin.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/points")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @GetMapping
    public ResponseEntity<List<PointDto>> readAllPoints() {
        try {
            List<PointDto> points = pointService.readAllPoints();
            return ResponseEntity.ok(points);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{pointDetailSeq}")
    public ResponseEntity<PointDto> readPoint(@PathVariable(value = "pointDetailSeq") Long pointDetailSeq) {
        try {
            PointDto point = pointService.readOne(pointDetailSeq);
            return ResponseEntity.ok(point);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<PointDto> createPoint(@RequestBody PointDto pointDto) {
        try {
            PointDto createdPoint = pointService.createPoint(pointDto);
            return ResponseEntity.ok(createdPoint);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
