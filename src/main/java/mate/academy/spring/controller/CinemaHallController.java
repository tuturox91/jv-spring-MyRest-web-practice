package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.CinemaHall;
import mate.academy.spring.model.dto.request.CinemaHallRequestDto;
import mate.academy.spring.model.dto.response.CinemaHallResponseDto;
import mate.academy.spring.service.CinemaHallService;
import mate.academy.spring.service.dto.mapping.DtoRequestMapper;
import mate.academy.spring.service.dto.mapping.DtoResponseMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final DtoRequestMapper<CinemaHallRequestDto, CinemaHall> dtoRequestMapper;
    private final DtoResponseMapper<CinemaHallResponseDto, CinemaHall> dtoResponseMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                DtoRequestMapper<CinemaHallRequestDto, CinemaHall>
                                        dtoRequestMapper,
                                DtoResponseMapper<CinemaHallResponseDto, CinemaHall>
                                        dtoResponseMapper) {
        this.cinemaHallService = cinemaHallService;
        this.dtoRequestMapper = dtoRequestMapper;
        this.dtoResponseMapper = dtoResponseMapper;
    }

    @PostMapping
    public CinemaHallResponseDto addCinemaHall(@RequestBody CinemaHallRequestDto dto) {
        CinemaHall cinemaHall = cinemaHallService.add(dtoRequestMapper.fromDto(dto));
        return dtoResponseMapper.toDto(cinemaHall);
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return cinemaHallService.getAll().stream()
                .map(dtoResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}
