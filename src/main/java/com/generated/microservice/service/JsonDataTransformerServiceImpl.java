package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.Marshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Service
@Slf4j
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public String transform(String xmlData) {
         String mappingJson = "{\n" +
                "\t\"mappings\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"sourceField\": \"Order.OrderId\",\n" +
                "\t\t\t\"targetField\": \"PurchaseOrder.Id\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"sourceField\": \"Order.OrderDate\",\n" +
                "\t\t\t\"targetField\": \"PurchaseOrder.Date\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"sourceField\": \"Order.Customer.CustomerId\",\n" +
                "\t\t\t\"targetField\": \"PurchaseOrder.Client.Id\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"sourceField\": \"Order.Customer.Name\",\n" +
                "\t\t\t\"targetField\": \"PurchaseOrder.Client.FullName\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"sourceField\": \"Order.Customer.Contact.Email\",\n" +
                "\t\t\t\"targetField\": \"PurchaseOrder.Client.Email\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"sourceField\": \"Order.Customer.Contact.Phone\",\n" +
                "\t\t\t\"targetField\": \"PurchaseOrder.Client.Phone\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"sourceField\": \"Order.Items.Item[]\",\n" +
                "\t\t\t\"targetField\": \"PurchaseOrder.Products.Product[]\",\n" +
                "\t\t\t\"children\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"sourceField\": \"ItemId\",\n" +
                "\t\t\t\t\t\"targetField\": \"Code\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"sourceField\": \"Description\",\n" +
                "\t\t\t\t\t\"targetField\": \"Name\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"sourceField\": \"Quantity\",\n" +
                "\t\t\t\t\t\"targetField\": \"Qty\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"sourceField\": \"Price\",\n" +
                "\t\t\t\t\t\"targetField\": \"UnitPrice\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"sourceField\": \"Order.Billing.CustomerId\",\n" +
                "\t\t\t\"targetField\": \"PurchaseOrder.Payment.CustomerId\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"sourceField\": \"Order.Billing.TotalAmount\",\n" +
                "\t\t\t\"targetField\": \"PurchaseOrder.Payment.Amount\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"sourceField\": \"Order.Billing.Paid\",\n" +
                "\t\t\t\"targetField\": \"PurchaseOrder.Payment.Status\",\n" +
                "\t\t\t\"transform\": \"booleanToPaidStatus\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";


        try {
            // 1. Unmarshal Source XML
            JAXBContext jaxbContextSource = JAXBContext.newInstance(Order.class);
            Unmarshaller unmarshallerSource = jaxbContextSource.createUnmarshaller();
            Order sourceOrder = (Order) unmarshallerSource.unmarshal(new StringReader(xmlData));


            // 2. Map and Transform
            JsonNode mappingRoot = objectMapper.readTree(mappingJson);
            PurchaseOrder targetPurchaseOrder = new PurchaseOrder();
            // ... (Mapping and transformation logic would go here based on mappingRoot and sourceOrder) ...

            //Example implementation
            //MappingHelper.mapValues(sourceOrder, targetPurchaseOrder, mappingRoot);

            // 3. Marshal Target XML
            JAXBContext jaxbContextTarget = JAXBContext.newInstance(PurchaseOrder.class);
            Marshaller marshallerTarget = jaxbContextTarget.createMarshaller();
            marshallerTarget.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // For pretty printing

            StringWriter sw = new StringWriter();
            marshallerTarget.marshal(targetPurchaseOrder, sw);
            return sw.toString();

        } catch (JAXBException e) {
            log.error("Error during transformation: {}", e.getMessage(), e);
            return null; // Or throw an exception depending on your error handling strategy
        } catch (Exception e) {
            log.error("Error during transformation: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public PurchaseOrder transformToPurchaseOrder(String xmlSource, String mappingJson) {
        try {
            // 1. Unmarshal XML source to Order object
            JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Order order = (Order) unmarshaller.unmarshal(new StringReader(xmlSource));


            // 2. (Placeholder) Map Order to PurchaseOrder using mappingJson
            //   This part requires specific mapping logic based on mappingJson.
            //   For now, basic mapping is provided:
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setId(order.getOrderId()); //Illustrative basic mapping

            //TODO: Implement detailed mapping based on the json mapping

            return purchaseOrder;


        } catch (JAXBException e) {
            log.error("Error transforming XML: {}", e.getMessage());
            return null;
        }
    }
}