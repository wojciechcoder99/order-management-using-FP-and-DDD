package wfarganu.order.domain.core.operational.operations;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.collection.List;
import lombok.Getter;
import wfarganu.ddd.BaseAggregate;
import wfarganu.order.domain.dtos.OrderItemDTO;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Domain Driven Design enforces to correctly set aggregate boundaries, otherwise they could be as 'God classes',
 * impossible to be easily changed. The technique used to do that is 'invariants modelling'. We need to distinguish
 * crucial operations which are stable e.g. adding a new item to the order causes the price recalculation.
 * <p>
 * Therefore, we will use User Stories to group terms in the context of consistent unit change.
 */
@BaseAggregate
final class Order {
    private final UUID orderId;
    @Getter
    private final List<OrderItemDTO> orderItems;
    @Getter
    private final BigDecimal totalPrice;

    public Order() {
        orderId = UUID.randomUUID();
        orderItems = List.of();
        totalPrice = BigDecimal.ZERO;
    }

    private Order(final UUID orderId, final BigDecimal totalPrice, final List<OrderItemDTO> orderItems) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public Order addItem(OrderItemDTO item) {
        return addItem().apply(item).transform(computeOrder());
    }

    private Function1<OrderItemDTO, List<OrderItemDTO>> addItem() {
        return orderItems::append;
    }

    private Function1<List<OrderItemDTO>, Order> computeOrder() {
        return orderItems -> createOrder().curried()
                .apply(calculateTotalPrice().apply(orderItems))
                .apply(orderItems);
    }

    private Function1<List<OrderItemDTO>, BigDecimal> calculateTotalPrice() {
        return calculateItemsPrice();
    }

    private static Function1<List<OrderItemDTO>, BigDecimal> calculateItemsPrice() {
        return items -> items.map(OrderItemDTO::totalPrice).fold(BigDecimal.ZERO, BigDecimal::add);
    }

    private Function2<BigDecimal, List<OrderItemDTO>, Order> createOrder() {
        return (totalPrice, items) -> new Order(orderId, totalPrice, items);
    }

    public Order placeOrder() {
        return new Order(orderId, null, null);
    }
}
