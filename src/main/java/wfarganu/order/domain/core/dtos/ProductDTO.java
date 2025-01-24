package wfarganu.order.domain.core.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDTO(String name, BigDecimal price, int quantity) {
}
