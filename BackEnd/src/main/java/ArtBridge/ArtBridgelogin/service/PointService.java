package ArtBridge.ArtBridgelogin.service;

import ArtBridge.ArtBridgelogin.controller.dto.webRTC.PointDto;
import ArtBridge.ArtBridgelogin.domain.Point;
import ArtBridge.ArtBridgelogin.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    @Transactional(readOnly = true)
    public List<PointDto> readAllPoints() {
        List<Point> points = pointRepository.readAll();

        return points.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PointDto readOne(Long pointDetailSeq) {
        Point point = pointRepository.readPointByDetailSeq(pointDetailSeq);

        return convertToDto(point);
    }

    @Transactional
    public PointDto createPoint(PointDto pointDto) {
        Point point = convertToEntity(pointDto);
        Point createdPoint = pointRepository.create(point);

        return convertToDto(createdPoint);
    }

    private Point convertToEntity(PointDto pointDto) {
        Point point = new Point();
        point.setPointDetailSeq(pointDto.getPointDetailSeq());
        point.setPointDetailId(pointDto.getPointDetailId());
        point.setPointDetailIsartist(pointDto.isPointDetailIsartist());
        point.setPointDetailTradePoint(pointDto.getPointDetailTradePoint());
        point.setPointDetailTradeDate(pointDto.getPointDetailTradeDate());
        point.setPointDetailTradeCategory(pointDto.getPointDetailTradeCategory());
        point.setPointDetailTradeTableSeq(pointDto.getPointDetailTradeTableSeq());

        return point;
    }

    private PointDto convertToDto(Point point) {
        PointDto pointDto = new PointDto();
        pointDto.setPointDetailSeq(point.getPointDetailSeq());
        pointDto.setPointDetailId(point.getPointDetailId());
        pointDto.setPointDetailIsartist(point.isPointDetailIsartist());
        pointDto.setPointDetailTradePoint(point.getPointDetailTradePoint());
        pointDto.setPointDetailTradeDate(point.getPointDetailTradeDate());
        pointDto.setPointDetailTradeCategory(point.getPointDetailTradeCategory());
        pointDto.setPointDetailTradeTableSeq(point.getPointDetailTradeTableSeq());

        return pointDto;
    }


}
