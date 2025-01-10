package wfarganu.order.application;

import wfarganu.ddd.ApplicationService;
import wfarganu.order.domain.core.Invoice;
import wfarganu.order.domain.dtos.OrderDTO;
import wfarganu.order.domain.factory.InvoiceFactory;


@ApplicationService
public class OrderApplicationService {

    /**
     * In first version let's take the order as only submitted orders are stored in the system (it might
     * change in the future version)
     * @param order order to be submitted
     * @return order invoice as the confirmation of the success
     */
    public Invoice submitOrder(OrderDTO order) {
        return InvoiceFactory.createEmptyInvoice();
    }
}
