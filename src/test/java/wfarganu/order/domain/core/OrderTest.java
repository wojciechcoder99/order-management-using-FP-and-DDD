package wfarganu.order.domain.core;

import org.junit.jupiter.api.Test;
import wfarganu.order.domain.dtos.OrderItemDTO;
import wfarganu.order.domain.dtos.ProductDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderTest {

    @Test
    public void shouldAddTwoItemsToOrder() {
        // given
        Order orderV0 = new Order();

        ProductDTO apple = ProductDTO.builder()
                .name("Apple")
                .price(new BigDecimal("1.23"))
                .build();
        OrderItemDTO orderItemApple = OrderItemDTO.builder()
                .product(apple)
                .reserved(5)
                .totalPrice(new BigDecimal("6.15"))
                .build();

        ProductDTO bag = ProductDTO.builder()
                .name("Dior bag")
                .price(new BigDecimal("1111"))
                .build();
        OrderItemDTO orderItemBag = OrderItemDTO.builder()
                .product(bag)
                .reserved(2)
                .totalPrice(new BigDecimal("2222"))
                .build();

        // when
        Order orderV1 = orderV0.addItem(orderItemApple);
        Order orderV2 = orderV1.addItem(orderItemBag);

        // then
        assertNotNull(orderV2);
        assertEquals(2, orderV2.getOrderItems().size());
        assertEquals(0, orderV2.getTotalPrice().compareTo(new BigDecimal("2228.15")));
    }

}