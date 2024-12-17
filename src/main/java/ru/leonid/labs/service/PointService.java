package ru.leonid.labs.service;

import ru.leonid.labs.dto.PointDTO;

import java.util.List;

public interface PointService {

    PointDTO createPoint(PointDTO dto);

    PointDTO getPointById(Long id);

    List<PointDTO> getAllPoints();

    PointDTO updatePoint(Long id, PointDTO pointDTO);

    boolean deletePoint(Long id);
}