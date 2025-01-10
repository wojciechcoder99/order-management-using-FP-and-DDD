package wfarganu.order.application;


import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import wfarganu.order.domain.core.Invoice;
import wfarganu.order.domain.dtos.OrderDTO;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The tests are designed according to the Behaviour Driven Development (BDD). It ensures that they
 * represent the user needs and reflect their expectations from the system.
 * <p>
 * Also, it is worth mentioning that functions are designed in Test Driven Development (TDD) approach,
 * meaning before implementing any logic, firstly test case should be written
 */
class OrderApplicationServiceTest {

    private final OrderApplicationService orderApplicationService = new OrderApplicationService();

    /**
     * User story 3: As a user, I want to submit an order and get the invoice as the confirmation of success
     */
    @Test
    public void shouldSubmitOrder() {
        // given
        OrderDTO orderV0 = new OrderDTO(UUID.randomUUID(), BigDecimal.ZERO, List.of());

        // when
        Invoice invoice = orderApplicationService.submitOrder(orderV0);

        // then
        assertNotNull(invoice);
    }
}