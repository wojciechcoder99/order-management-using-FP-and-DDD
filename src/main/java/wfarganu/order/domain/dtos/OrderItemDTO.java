package wfarganu.order.domain.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderItemDTO(ProductDTO product, int reserved, BigDecimal totalPrice) {
}
