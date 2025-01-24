package wfarganu.order.domain.core.operational.operations;

import io.vavr.collection.Seq;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import wfarganu.order.domain.core.dtos.OrderItemDTO;
import wfarganu.order.domain.core.dtos.ProductDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private static OrderItemDTO orderItemBag;

    // TODO wfarganu: Prepare test data factories
    @BeforeAll
    public static void setUp() {
        ProductDTO bag = ProductDTO.builder()
                .name("Dior bag")
                .price(new BigDecimal("1111"))
                .build();
        orderItemBag = OrderItemDTO.builder()
                .product(bag)
                .reserved(2)
                .totalPrice(new BigDecimal("2222"))
                .build();

    }

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
        Order orderV1 = orderV0.addItem(orderItemBag);

        // when
        Either<Seq<String>, Order> result = orderV1.placeOrder();

        // then
        assertTrue(result.isRight());
        assertEquals(Order.OrderStatus.COMPLETED, result.get().getStatus());
    }
}

