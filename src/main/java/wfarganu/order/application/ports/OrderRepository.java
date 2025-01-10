package wfarganu.order.application.ports;

import wfarganu.order.domain.dtos.OrderDTO;

import java.util.UUID;

public interface OrderRepository {
    UUID submit(OrderDTO order);
}
