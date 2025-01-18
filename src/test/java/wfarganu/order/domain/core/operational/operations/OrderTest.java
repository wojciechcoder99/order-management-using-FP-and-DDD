package wfarganu.order.domain.core.operational.operations;

import org.junit.jupiter.api.Test;
import wfarganu.order.domain.dtos.OrderItemDTO;
import wfarganu.order.domain.dtos.ProductDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderTest {

    /**
     * User Story 1: As a user, I want to create an empty order so that I can start adding items.
     */
    @Test
    public void shouldCreateAnEmptyOrder() {
        Order order = new Order();

        assertNotNull(order);
        assertEquals(0, order.getOrderItems().size());
        assertEquals(0, order.getTotalPrice().compareTo(BigDecimal.ZERO));
    }

    /**
     * User Story 2: As a user, I want to add items to an order so that I can build my order.
     */
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

    /**
     * User story 3: As a user, I want to place an order so that I can purchase items from the catalog
     */
    @Test
    public void shouldPlaceOrder() {
        // given
       Order orderV0 = new Order();

       // when
        Order order = orderV0.placeOrder();

        // then
        assertNotNull(order);
    }
}

