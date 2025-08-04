package com.generated.microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generated.microservice.model.source.Billing;
import com.generated.microservice.model.source.Contact;
import com.generated.microservice.model.source.Customer;
import com.generated.microservice.model.source.Item;
import com.generated.microservice.model.source.Items;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.Client;
import com.generated.microservice.model.target.Payment;
import com.generated.microservice.model.target.Product;
import com.generated.microservice.model.target.Products;
import com.generated.microservice.model.target.PurchaseOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class JsonDataTransformerServiceImplTest {

    @InjectMocks
    private JsonDataTransformerServiceImpl jsonDataTransformerService;

    @Test
    void testTransform() throws Exception {
        Order sourceOrder = createSourceOrder();
        PurchaseOrder purchaseOrder = jsonDataTransformerService.transform(sourceOrder);


        assertNotNull(purchaseOrder);
        assertEquals(sourceOrder.getOrderId(), purchaseOrder.getId());
        assertEquals(sourceOrder.getOrderDate(), purchaseOrder.getDate());

        Customer sourceCustomer = sourceOrder.getCustomer();
        Client targetClient = purchaseOrder.getClient();
        assertEquals(sourceCustomer.getCustomerId(), targetClient.getId());
        assertEquals(sourceCustomer.getName(), targetClient.getFullName());

        Contact sourceContact = sourceCustomer.getContact();
        com.generated.microservice.model.target.Contact targetContact = targetClient.getContact();

        assertEquals(sourceContact.getEmail(), targetContact.getEmail());
        assertEquals(sourceContact.getPhone(), targetContact.getPhone());

        Items sourceItems = sourceOrder.getItems();
        Products targetProducts = purchaseOrder.getProducts();

        List<Item> sourceItemList = sourceItems.getItem();
        List<Product> targetProductList = targetProducts.getProduct();

        assertEquals(sourceItemList.size(), targetProductList.size());


        for (int i = 0; i < sourceItemList.size(); i++) {

            Item sourceItem = sourceItemList.get(i);
            Product targetProduct = targetProductList.get(i);

            assertEquals(sourceItem.getItemId(), targetProduct.getCode());
            assertEquals(sourceItem.getDescription(), targetProduct.getName());
            assertEquals(sourceItem.getQuantity(), targetProduct.getQty());
            assertEquals(sourceItem.getPrice(), targetProduct.getUnitPrice());
        }


        Billing sourceBilling = sourceOrder.getBilling();
        Payment targetPayment = purchaseOrder.getPayment();

        assertEquals(sourceBilling.getCustomerId(), targetPayment.getCustomerId());
        assertEquals(sourceBilling.getTotalAmount(), targetPayment.getAmount());
        assertEquals(sourceBilling.isPaid(), targetPayment.getStatus());

    }




    private Order createSourceOrder() {
        Order order = new Order();
        order.setOrderId("12345");
        order.setOrderDate("2023-12-25");

        Customer customer = new Customer();
        customer.setCustomerId("CUST001");
        customer.setName("John Doe");

        Contact contact = new Contact();
        contact.setEmail("john.doe@example.com");
        contact.setPhone("123-456-7890");
        customer.setContact(contact);
        order.setCustomer(customer);

        Items items = new Items();
        List<Item> itemList = new ArrayList<>();

        Item item1 = new Item();
        item1.setItemId("ITEM001");
        item1.setDescription("Product A");
        item1.setQuantity(2);
        item1.setPrice(10.00);
        itemList.add(item1);


        Item item2 = new Item();
        item2.setItemId("ITEM002");
        item2.setDescription("Product B");
        item2.setQuantity(1);
        item2.setPrice(20.00);
        itemList.add(item2);

        items.setItem(itemList);
        order.setItems(items);


        Billing billing = new Billing();
        billing.setCustomerId("CUST001");
        billing.setTotalAmount(40.00);
        billing.setPaid(true);

        order.setBilling(billing);



        return order;
    }
}