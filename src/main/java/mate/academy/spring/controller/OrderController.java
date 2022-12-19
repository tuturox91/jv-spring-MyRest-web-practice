package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.mapper.DtoResponseMapper;
import mate.academy.spring.model.Order;
import mate.academy.spring.model.ShoppingCart;
import mate.academy.spring.model.dto.response.OrderResponseDto;
import mate.academy.spring.service.OrderService;
import mate.academy.spring.service.ShoppingCartService;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ShoppingCartService cartService;
    private final UserService userService;
    private final DtoResponseMapper<OrderResponseDto, Order> responseMapper;

    public OrderController(OrderService orderService,
                           ShoppingCartService cartService, UserService userService,
                           DtoResponseMapper<OrderResponseDto, Order> responseMapper) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
        this.responseMapper = responseMapper;
    }

    @PostMapping("/complete")
    public OrderResponseDto getCompletedOrders(@RequestParam Long userId) {
        ShoppingCart userCart = cartService.getByUser(userService.get(userId));
        return responseMapper.toDto(orderService.completeOrder(userCart));
    }

    @GetMapping("/{userId}")
    public List<OrderResponseDto> getOrderByUser(@PathVariable Long userId) {
        return orderService.getOrdersHistory(userService.get(userId))
                .stream().map(responseMapper::toDto).collect(Collectors.toList());
    }
}
