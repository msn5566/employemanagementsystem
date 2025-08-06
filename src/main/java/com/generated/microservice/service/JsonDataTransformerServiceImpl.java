package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.source.Item;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {

    private final ObjectMapper objectMapper;

    public JsonDataTransformerServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String transform(String sourceXml, String mappingJson) throws JAXBException, IOException {
        JAXBContext sourceContext = JAXBContext.newInstance(Order.class);
        Unmarshaller unmarshaller = sourceContext.createUnmarshaller();
        Order sourceOrder = (Order) unmarshaller.unmarshal(new StringReader(sourceXml));


        JsonNode mappingRoot = objectMapper.readTree(mappingJson);
        com.generated.microservice.model.target.PurchaseOrder targetOrder = transformPOJO(sourceOrder, mappingRoot);


        JAXBContext targetContext = JAXBContext.newInstance(com.generated.microservice.model.target.PurchaseOrder.class);
        Marshaller marshaller = targetContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(targetOrder, writer);
        return writer.toString();

    }


    public com.generated.microservice.model.target.PurchaseOrder transformPOJO(Order sourceOrder, JsonNode mappingRoot) {
        com.generated.microservice.model.target.PurchaseOrder targetOrder = new com.generated.microservice.model.target.PurchaseOrder();
        targetOrder.setId(sourceOrder.getOrderId());
        targetOrder.setDate(sourceOrder.getOrderDate());

        com.generated.microservice.model.source.Customer sourceCustomer = sourceOrder.getCustomer();
        com.generated.microservice.model.source.Contact sourceContact = sourceCustomer != null ? sourceCustomer.getContact() : null;

        com.generated.microservice.model.target.Client targetClient = new com.generated.microservice.model.target.Client();
        targetClient.setFullName(sourceCustomer != null ? sourceCustomer.getName() : null);
        targetClient.setEmail(sourceContact != null ? sourceContact.getEmail() : null);
        targetClient.setPhone(sourceContact != null ? sourceContact.getPhone() : null);
        targetOrder.setClient(targetClient);



        com.generated.microservice.model.target.Products targetProducts = new com.generated.microservice.model.target.Products();
        com.generated.microservice.model.source.Items sourceItems = sourceOrder.getItems();
        List<Item> sourceItemList = sourceItems != null ? sourceItems.getItem() : null;
        List<com.generated.microservice.model.target.Product> targetProductList = targetProducts.getProduct();

        if(sourceItemList != null) {
            for (Item sourceItem : sourceItemList) {
                com.generated.microservice.model.target.Product targetProduct = new com.generated.microservice.model.target.Product();
                targetProduct.setName(sourceItem.getDescription());
                targetProduct.setQty(sourceItem.getQuantity());
                targetProduct.setUnitPrice(BigDecimal.valueOf(sourceItem.getPrice()));
                targetProductList.add(targetProduct);
            }
        }

        targetOrder.setProducts(targetProducts);


        com.generated.microservice.model.target.Payment targetPayment = new com.generated.microservice.model.target.Payment();
        com.generated.microservice.model.source.Billing sourceBilling = sourceOrder.getBilling();
        targetPayment.setCustomerId(sourceBilling != null ? sourceBilling.getCustomerId() : null);
        targetPayment.setAmount(sourceBilling != null ? BigDecimal.valueOf(sourceBilling.getTotalAmount()) : null);
        targetPayment.setStatus(sourceBilling != null ? booleanToPaidStatus(sourceBilling.isPaid()) : null);
        targetOrder.setPayment(targetPayment);

        return targetOrder;
    }




    public String booleanToPaidStatus(boolean paid) {
        return paid ? "Paid" : "Unpaid";
    }

}