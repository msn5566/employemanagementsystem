
package com.generated.microservice.service;

import com.generated.microservice.model.source.*;
import com.generated.microservice.model.target.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
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

    @Test
    void shouldTransformOrder_whenValidOrderIsProvided() {
        // Given
        Order order = createSampleOrder();

        // When
        PurchaseOrder purchaseOrder = orderTransformationService.transformOrder(order);

        // Then
        assertPurchaseOrder(purchaseOrder, order);
    }

    @Test
    void shouldTransformOrder_whenOrderHasNoItems() {
        // Given
        Order order = createSampleOrder();
        order.setItems(new Items(List.of())); // Empty items list

        // When
        PurchaseOrder purchaseOrder = orderTransformationService.transformOrder(order);

        // Then
        assertEquals(0, purchaseOrder.getProducts().getProduct().size());
        // Assert other fields as before
        assertPurchaseOrder(purchaseOrder, order);
    }



    private Order createSampleOrder() {
        Order order = new Order();
        order.setOrderId("12345");
        order.setOrderDate("2024-05-15");

        Customer customer = new Customer();
        customer.setCustomerId("CUST001");
        customer.setName("John Doe");

        Contact contact = new Contact();
        contact.setEmail("john.doe@example.com");
        contact.setPhone("123-456-7890");
        customer.setContact(contact);
        order.setCustomer(customer);



        Items items = new Items();
        List<Item> itemList = List.of(
                new Item("ITEM001", "Product A", 2, 10.00),
                new Item("ITEM002", "Product B", 1, 25.50)
        );
        items.setItem(itemList);

        order.setItems(items);


        Billing billing = new Billing();
        billing.setCustomerId("CUST001");
        billing.setTotalAmount(45.50);
        billing.setPaid(true);
        order.setBilling(billing);

        return order;
    }


    private void assertPurchaseOrder(PurchaseOrder purchaseOrder, Order order) {
        assertEquals(order.getOrderId(), purchaseOrder.getId());
        assertEquals(order.getOrderDate(), purchaseOrder.getDate());

        Client client = purchaseOrder.getClient();
        assertEquals(order.getCustomer().getCustomerId(), client.getId());
        assertEquals(order.getCustomer().getName(), client.getFullName());
        assertEquals(order.getCustomer().getContact().getEmail(), client.getEmail());
        assertEquals(order.getCustomer().getContact().getPhone(), client.getPhone());


        List<Product> productList = purchaseOrder.getProducts().getProduct();
        List<Item> itemList = order.getItems().getItem();


        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            Product product = productList.get(i);

            assertEquals(item.getItemId(), product.getCode());
            assertEquals(item.getDescription(), product.getName());
            assertEquals(item.getQuantity(), product.getQty());
            assertEquals(item.getPrice(), product.getUnitPrice());
        }

        Payment payment = purchaseOrder.getPayment();
        assertEquals(order.getBilling().getCustomerId(), payment.getCustomerId());
        assertEquals(order.getBilling().getTotalAmount(), payment.getAmount());
        assertEquals("Paid", payment.getStatus());


    }
}