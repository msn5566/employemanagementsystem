
package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.Client;
import com.generated.microservice.model.target.Payment;
import com.generated.microservice.model.target.Product;
import com.generated.microservice.model.target.Products;
import com.generated.microservice.model.target.PurchaseOrder;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String transform(String sourceXml, String mappingJson) throws JAXBException, IOException {
        Order order = unmarshalSourceXml(sourceXml);
        JsonNode mapping = objectMapper.readTree(mappingJson);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(order.getOrderId());
        purchaseOrder.setDate(order.getOrderDate());


        Client client = new Client();
        client.setId(order.getCustomer().getCustomerId());
        client.setFullName(order.getCustomer().getName());
        client.setEmail(order.getCustomer().getContact().getEmail());
        client.setPhone(order.getCustomer().getContact().getPhone());
        purchaseOrder.setClient(client);


        List<Product> productList = order.getItems().getItem().stream()
                .map(item -> new Product(item.getItemId(), item.getDescription(), item.getQuantity(), item.getPrice()))
                .collect(Collectors.toList());

        Products products = new Products(productList);
        purchaseOrder.setProducts(products);


        Payment payment = new Payment();
        payment.setCustomerId(order.getBilling().getCustomerId());
        payment.setAmount(order.getBilling().getTotalAmount());
        payment.setStatus(booleanToPaidStatus(order.getBilling().isPaid()));
        purchaseOrder.setPayment(payment);


        return marshalTargetXml(purchaseOrder);
    }


    protected Order unmarshalSourceXml(String sourceXml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(sourceXml);
        return (Order) unmarshaller.unmarshal(reader);
    }

    private String marshalTargetXml(PurchaseOrder targetOrder) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PurchaseOrder.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(targetOrder, writer);
        return writer.toString();
    }


    // Helper functions for transformation and field access
    private String booleanToPaidStatus(boolean paid) {
        return paid ? "Paid" : "Unpaid";
    }

}