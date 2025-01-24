package wfarganu.order.domain.core.operational.operations;

import io.vavr.Function1;
import io.vavr.Function3;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import lombok.Getter;
import wfarganu.ddd.BaseAggregate;
import wfarganu.order.domain.core.dtos.OrderItemDTO;

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
    public enum OrderStatus {
        DRAFT, IN_PROGRESS, COMPLETED
    }

    private final UUID orderId;
    @Getter
    private final List<OrderItemDTO> orderItems;
    @Getter
    private final BigDecimal totalPrice;
    @Getter
    private final OrderStatus status;

    public Order() {
        orderId = UUID.randomUUID();
        orderItems = List.of();
        totalPrice = BigDecimal.ZERO;
        status = OrderStatus.DRAFT;
    }

    private Order(final UUID orderId, final BigDecimal totalPrice, final List<OrderItemDTO> orderItems, OrderStatus status) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
        this.status = status;
    }

    public Order addItem(OrderItemDTO item) {
        return orderItems.prepend(item).transform(computeOrder());
    }

    private Function1<List<OrderItemDTO>, Order> computeOrder() {
        return orderItems -> createOrder().curried()
                .apply(calculateTotalPrice().apply(orderItems))
                .apply(orderItems)
                .apply(OrderStatus.IN_PROGRESS);
    }

    private Function1<List<OrderItemDTO>, BigDecimal> calculateTotalPrice() {
        // TODO wfarganu: here clouser can be used e.g. tax calculation
        return calculateItemsPrice();
    }

    private Function1<List<OrderItemDTO>, BigDecimal> calculateItemsPrice() {
        return items -> items.map(OrderItemDTO::totalPrice).fold(BigDecimal.ZERO, BigDecimal::add);
    }

    private Function3<BigDecimal, List<OrderItemDTO>, OrderStatus, Order> createOrder() {
        return (totalPrice, items, orderStatus) -> new Order(orderId, totalPrice, items, orderStatus);
    }

    public Either<Seq<String>, Order> placeOrder() {
        return validate().toEither().map(markAsCompleted());
    }

    private Validation<Seq<String>, Order> validate() {
        return Validation.combine(
                hasValidStatus(),
                hasValidItems())
            .ap((status, items) -> this);
    }

    private Validation<String, OrderStatus> hasValidStatus() {
        return this.status == OrderStatus.IN_PROGRESS ? Validation.valid(status)
                : Validation.invalid("Status is not correct");
    }

    private Validation<String, List<OrderItemDTO>> hasValidItems() {
        return !orderItems.isEmpty() ? Validation.valid(orderItems)
                : Validation.invalid("Order items list is empty");
    }

    private Function1<Order, Order> markAsCompleted() {
        return order -> createOrder().apply(order.totalPrice, order.orderItems, OrderStatus.COMPLETED);
    }
}
