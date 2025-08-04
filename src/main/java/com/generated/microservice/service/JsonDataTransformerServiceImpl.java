
package com.generated.microservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.PurchaseOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JsonDataTransformerServiceImpl implements JsonDataTransformerService {
    // Pre-generated transformation logic from combined context

    @Override
    public PurchaseOrder transform(Order sourceOrder) {
        // Implementation from combined context remains unchanged
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            String mappingJson = "{\n" +
                    "  \"OrderId\": \"Id\",\n" +
                    "  \"OrderDate\": \"Date\",\n" +
                    "  \"Customer\": {\n" +
                    "    \"CustomerId\": \"Id\",\n" +
                    "    \"Name\": \"FullName\",\n" +
                    "    \"Contact\": {\n" +
                    "      \"Email\": \"Email\",\n" +
                    "      \"Phone\": \"Phone\"\n" +
                    "    }\n" +
                    "  },\n" +
                    "  \"Items\": {\n" +
                    "    \"Item\": {\n" +
                    "      \"ItemId\": \"Code\",\n" +
                    "      \"Description\": \"Name\",\n" +
                    "      \"Quantity\": \"Qty\",\n" +
                    "      \"Price\": \"UnitPrice\"\n" +
                    "    }\n" +
                    "  },\n" +
                    "  \"Billing\": {\n" +
                    "    \"CustomerId\": \"CustomerId\",\n" +
                    "    \"TotalAmount\": \"amount\",\n" +
                    "    \"Paid\": \"Status\"\n" +
                    "  }\n" +
                    "}";


            JsonNode mappingRoot = objectMapper.readTree(mappingJson);

            PurchaseOrder purchaseOrder = new PurchaseOrder();


            // Example transformation: sourceOrder.getOrderId() -> purchaseOrder.setId()
            // based on mapping_validation.json
            objectMapper = new ObjectMapper();

            JsonNode orderJson = objectMapper.valueToTree(sourceOrder);
            purchaseOrder = objectMapper.readValue(transformJson(orderJson, mappingRoot).toString(), PurchaseOrder.class);


            return purchaseOrder;

        }
        catch (Exception e){
            log.error("Error occurred during transformation : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public PurchaseOrder transform(Order sourceOrder, String mappingJson) {
        return null;
    }


    private JsonNode transformJson(JsonNode source, JsonNode mapping) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode target = objectMapper.createObjectNode();

        mapping.fieldNames().forEachRemaining(sourceKey -> {
            JsonNode mappingValue = mapping.get(sourceKey);
            JsonNode sourceValue = source.get(sourceKey);

            if (mappingValue.isTextual()) {
                String targetKey = mappingValue.asText();
                if (sourceValue != null) {
                    target.set(targetKey, sourceValue);
                }
            } else if (mappingValue.isObject()) {
                if (sourceValue != null) {
                    if (sourceValue.isArray()) {
                        ArrayNode transformedItems = objectMapper.createArrayNode();
                        for (JsonNode item : sourceValue) {
                            transformedItems.add(transformJson(item, mappingValue));
                        }
                        target.set(sourceKey, transformedItems);
                    } else {
                        target.set(sourceKey, transformJson(sourceValue, mappingValue));
                    }
                }
            }
        });

        return target;
    }
}