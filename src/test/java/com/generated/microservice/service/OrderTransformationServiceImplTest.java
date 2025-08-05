package com.generated.microservice.service;

import com.generated.microservice.model.source.Billing;
import com.generated.microservice.model.source.Contact;
import com.generated.microservice.model.source.Customer;
import com.generated.microservice.model.source.Item;
import com.generated.microservice.model.source.Items;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.Client;
import com.generated.microservice.model.target.Payment;
import com.generated.microservice.model.target.Product;
import com.generated.microservice.model.target.PurchaseOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderTransformationServiceImplTest {

    @InjectMocks
    private OrderTransformationServiceImpl orderTransformationService;

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setOrderId("123");
        order.setOrderDate("2024-07-24");

        Customer customer = new Customer();
        customer.setCustomerId("456");
        customer.setName("Test Customer");

        Contact contact = new Contact();
        contact.setEmail("test@example.com");
        contact.setPhone("123-456-7890");
        customer.setContact(contact);
        order.setCustomer(customer);


        Items items = new Items();
        Item item1 = new Item();
        item1.setItemId("789");
        item1.setDescription("Test Item 1");
        item1.setQuantity(2);
        item1.setPrice(BigDecimal.valueOf(10.00));

        Item item2 = new Item();
        item2.setItemId("012");
        item2.setDescription("Test Item 2");
        item2.setQuantity(1);
        item2.setPrice(BigDecimal.valueOf(5.50));


        items.setItem(List.of(item1, item2));
        order.setItems(items);


        Billing billing = new Billing();
        billing.setCustomerId("456");
        billing.setTotalAmount(BigDecimal.valueOf(25.50));
        billing.setPaid(true);


        order.setBilling(billing);
    }

    @Test
    void shouldTransformOrderCorrectly() {


        PurchaseOrder purchaseOrder = orderTransformationService.transformOrder(order);

        assertEquals("123", purchaseOrder.getId());
        assertEquals("2024-07-24", purchaseOrder.getDate());

        Client client = purchaseOrder.getClient();
        assertEquals("456", client.getId());
        assertEquals("Test Customer", client.getFullName());
        assertEquals("test@example.com", client.getEmail());
        assertEquals("123-456-7890", client.getPhone());

        List<Product> products = purchaseOrder.getProducts().getProduct();
        assertEquals(2, products.size());
        assertEquals("789", products.get(0).getId());
        assertEquals("Test Item 1", products.get(0).getDescription());
        assertEquals(2, products.get(0).getQuantity());
        assertEquals(BigDecimal.valueOf(10.00), products.get(0).getPrice());


        assertEquals("012", products.get(1).getId());
        assertEquals("Test Item 2", products.get(1).getDescription());
        assertEquals(1, products.get(1).getQuantity());
        assertEquals(BigDecimal.valueOf(5.50), products.get(1).getPrice());

        Payment payment = purchaseOrder.getPayment();
        assertEquals("456", payment.getCustomerId());
        assertEquals(BigDecimal.valueOf(25.50), payment.getAmount());
        assertEquals("Paid", payment.getStatus());
    }


    @Test
    void shouldSetPaymentStatusToUnpaid_whenPaidIsFalse() {
        order.getBilling().setPaid(false);
        PurchaseOrder purchaseOrder = orderTransformationService.transformOrder(order);
        assertEquals("Unpaid", purchaseOrder.getPayment().getStatus());

    }


    @Test
    void shouldHandleNullOrderInput() {

        PurchaseOrder purchaseOrder = orderTransformationService.transformOrder(null);
        assertEquals(null, purchaseOrder); // Expecting NullPointerException

    }




}