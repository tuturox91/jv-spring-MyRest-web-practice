package mate.academy.spring.controller;

import mate.academy.spring.mapper.DtoResponseMapper;
import mate.academy.spring.model.ShoppingCart;
import mate.academy.spring.model.dto.response.ShoppingCartResponseDto;
import mate.academy.spring.service.MovieSessionService;
import mate.academy.spring.service.ShoppingCartService;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService sessionService;

    private final UserService userService;

    private final DtoResponseMapper<ShoppingCartResponseDto, ShoppingCart> cartDtoResponseMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieSessionService sessionService, UserService userService,
                                  DtoResponseMapper<ShoppingCartResponseDto,
                                          ShoppingCart> cartDtoResponseMapper) {
        this.shoppingCartService = shoppingCartService;
        this.sessionService = sessionService;
        this.userService = userService;
        this.cartDtoResponseMapper = cartDtoResponseMapper;
    }

    @PutMapping("/movie-sessions")
    public void addMovieSession(@RequestParam Long userId, @RequestParam Long sessionId) {
        shoppingCartService.addSession(sessionService.get(sessionId), userService.get(userId));
    }

    @GetMapping("/{userId}")
    public ShoppingCartResponseDto addMovieSession(@PathVariable Long userId) {
        return cartDtoResponseMapper.toDto(shoppingCartService.getByUser(userService.get(userId)));
    }

}
