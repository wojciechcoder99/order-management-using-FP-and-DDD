package wfarganu.order.domain.factory;

import wfarganu.order.domain.core.operational.operations.Invoice;

public final class InvoiceFactory {
    public static Invoice createEmptyInvoice() {
        return new Invoice();
    }
}
