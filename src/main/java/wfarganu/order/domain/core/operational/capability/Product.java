package wfarganu.order.domain.core.operational.capability;

import java.math.BigDecimal;

record Product(String name, BigDecimal price, int quantity) {
    public Product withUpdatedPrice(BigDecimal updatedPrice) {
        return new Product(name, updatedPrice, quantity);
    }
}
