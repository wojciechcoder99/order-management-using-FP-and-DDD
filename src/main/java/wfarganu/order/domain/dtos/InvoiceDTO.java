package wfarganu.order.domain.dtos;

import java.time.Instant;
import java.util.UUID;

public record InvoiceDTO(UUID uuid, OrderDTO order, Instant issueDate) {
}
