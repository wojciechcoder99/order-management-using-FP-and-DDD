package wfarganu.order.domain.core.dtos;

import io.vavr.collection.List;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record OrderDTO(UUID uuid, BigDecimal totalPrice, List<OrderItemDTO> orderedItems) {
}
