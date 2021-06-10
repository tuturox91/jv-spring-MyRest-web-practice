package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.Movie;
import mate.academy.spring.model.dto.request.MovieRequestDto;
import mate.academy.spring.model.dto.response.MovieResponseDto;
import mate.academy.spring.service.MovieService;
import mate.academy.spring.service.dto.mapping.DtoRequestMapper;
import mate.academy.spring.service.dto.mapping.DtoResponseMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final DtoRequestMapper<MovieRequestDto, Movie> dtoRequestMapper;
    private final DtoResponseMapper<MovieResponseDto, Movie> dtoResponseMapper;

    public MovieController(MovieService movieService,
                           DtoRequestMapper<MovieRequestDto, Movie> dtoRequestMapper,
                           DtoResponseMapper<MovieResponseDto, Movie> dtoResponseMapper) {
        this.movieService = movieService;
        this.dtoRequestMapper = dtoRequestMapper;
        this.dtoResponseMapper = dtoResponseMapper;
    }

    @PostMapping
    public MovieResponseDto addMovie(@RequestBody MovieRequestDto dto) {
        Movie movie = movieService.add(dtoRequestMapper.fromDto(dto));
        return dtoResponseMapper.toDto(movie);
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(dtoResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}
