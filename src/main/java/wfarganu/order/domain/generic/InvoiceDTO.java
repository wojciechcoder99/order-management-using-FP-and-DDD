package wfarganu.order.domain.generic;

import wfarganu.order.domain.core.dtos.OrderDTO;

import java.time.Instant;
import java.util.UUID;

public record InvoiceDTO(UUID uuid, OrderDTO order, Instant issueDate) {
}
