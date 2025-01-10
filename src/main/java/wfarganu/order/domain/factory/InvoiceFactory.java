package wfarganu.order.domain.factory;

import wfarganu.order.domain.core.Invoice;

public final class InvoiceFactory {
    public static Invoice createEmptyInvoice() {
        return new Invoice();
    }
}
