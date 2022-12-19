package mate.academy.spring.mapper.impl.response;

import java.util.stream.Collectors;
import mate.academy.spring.mapper.DtoResponseMapper;
import mate.academy.spring.model.ShoppingCart;
import mate.academy.spring.model.Ticket;
import mate.academy.spring.model.dto.response.ShoppingCartResponseDto;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper
        implements DtoResponseMapper<ShoppingCartResponseDto, ShoppingCart> {

    private UserService userService;

    @Override
    public ShoppingCartResponseDto toDto(ShoppingCart cart) {
        ShoppingCartResponseDto cartResponseDto = new ShoppingCartResponseDto();
        cartResponseDto.setId(cartResponseDto.getId());
        cartResponseDto.setTicketsId(cart.getTickets().stream()
                .map(Ticket::getId).collect(Collectors.toList()));
        cartResponseDto.setUserId(cart.getUser().getId());
        return cartResponseDto;
    }
}
