package wfarganu.order.domain.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDTO(String name, BigDecimal price, int quantity) {
}
