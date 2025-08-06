package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generated.microservice.model.source.Billing;
import com.generated.microservice.model.source.Contact;
import com.generated.microservice.model.source.Customer;
import com.generated.microservice.model.source.Item;
import com.generated.microservice.model.source.Items;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.Client;
import com.generated.microservice.model.target.Payment;
import com.generated.microservice.model.target.Products;
import com.generated.microservice.model.target.PurchaseOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JsonDataTransformerServiceImplTest {


    @InjectMocks
    private JsonDataTransformerServiceImpl jsonDataTransformerService;

    @Mock
    private ObjectMapper objectMapper;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void shouldTransform_whenValidInput() throws IOException {
        String sourceXml = "<Order><orderId>123</orderId></Order>";
        String mappingJson = "{}";
        Order order = new Order();
        order.setOrderId("123");
        JsonNode mapping = objectMapper.readTree(mappingJson);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId("123");


        when(objectMapper.readValue(sourceXml, Order.class)).thenReturn(order);

        when(objectMapper.readTree(mappingJson)).thenReturn(mapping);
        when(jsonDataTransformerService.transformPOJO(order, mapping)).thenReturn(purchaseOrder);
        when(objectMapper.writeValueAsString(purchaseOrder)).thenReturn("{\"id\":\"123\"}");




        String result = jsonDataTransformerService.transform(sourceXml, mappingJson);


        assertNotNull(result);


    }



    @Test
    void shouldTransformPOJO_whenValidInput() throws IOException {
        Order order = createSourceOrder();
        String mappingJson = "{}";
        JsonNode mapping = objectMapper.readTree(mappingJson);
        Client client = new Client();
        client.setFullName("John Doe");

        when(objectMapper.convertValue(order.getCustomer(), Client.class))
                .thenReturn(client);
        Payment payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(100.00));
        payment.setStatus("Paid");

        when(objectMapper.convertValue(order.getBilling(), Payment.class))
                .thenReturn(payment);

        Products products = new Products();
        List<com.generated.microservice.model.target.Product> productList = new ArrayList<>();
        com.generated.microservice.model.target.Product product = new com.generated.microservice.model.target.Product();
        product.setName("Test Product");
        productList.add(product);
        products.setProduct(productList);
        when(objectMapper.convertValue(order.getItems(), Products.class))
                .thenReturn(products);
        PurchaseOrder result = jsonDataTransformerService.transformPOJO(order, mapping);

        assertNotNull(result);
        assertNotNull(result.getPayment());
        assertEquals("Paid", result.getPayment().getStatus());
        assertEquals(BigDecimal.valueOf(100.00), result.getPayment().getAmount());

    }


    @Test
    void shouldTransformPOJO_whenClientIsNull() throws IOException {
        Order order = createSourceOrder();
        order.setCustomer(null); // Setting customer to null

        String mappingJson = "{}";
        JsonNode mapping = objectMapper.readTree(mappingJson);

        Payment payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(100.00));
        payment.setStatus("Paid");

        when(objectMapper.convertValue(order.getBilling(), Payment.class))
                .thenReturn(payment);
        Products products = new Products();
        List<com.generated.microservice.model.target.Product> productList = new ArrayList<>();
        com.generated.microservice.model.target.Product product = new com.generated.microservice.model.target.Product();
        product.setName("Test Product");
        productList.add(product);
        products.setProduct(productList);

        when(objectMapper.convertValue(order.getItems(), Products.class))
                .thenReturn(products);


        PurchaseOrder result = jsonDataTransformerService.transformPOJO(order, mapping);


        assertNotNull(result);
        assertNotNull(result.getPayment());
        assertEquals("Paid", result.getPayment().getStatus());
        assertEquals(BigDecimal.valueOf(100.00), result.getPayment().getAmount());
        assertNull(result.getClient());
    }

    @Test
    void shouldReturnPaid_whenPaidIsTrue() {
        String result = jsonDataTransformerService.booleanToPaidStatus(true);
        assertEquals("Paid", result);
    }

    @Test
    void shouldReturnUnpaid_whenPaidIsFalse() {
        String result = jsonDataTransformerService.booleanToPaidStatus(false);
        assertEquals("Unpaid", result);
    }



    private Order createSourceOrder() {
        Order order = new Order();
        order.setOrderId("123");
        order.setOrderDate("2024-07-24");

        Customer customer = new Customer();
        Contact contact = new Contact();
        contact.setFirstName("John");
        contact.setLastName("Doe");
        customer.setContact(contact);
        customer.setCustomerId("customer1");
        order.setCustomer(customer);


        Billing billing = new Billing();
        billing.setPaid(true);
        billing.setTotalAmount(BigDecimal.valueOf(100.00));

        order.setBilling(billing);

        Items items = new Items();
        List<Item> itemList = new ArrayList<>();
        Item item1 = new Item();
        item1.setItemId("item1");
        item1.setPrice(50.00);
        itemList.add(item1);
        items.setItem(itemList);


        order.setItems(items);

        return order;
    }




}