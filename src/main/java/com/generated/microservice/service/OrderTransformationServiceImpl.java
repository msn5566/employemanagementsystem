package com.generated.microservice.service;

import com.generated.microservice.model.source.Order;
import com.generated.microservice.model.target.Client;
import com.generated.microservice.model.target.Payment;
import com.generated.microservice.model.target.Product;
import com.generated.microservice.model.target.Products;
import com.generated.microservice.model.target.PurchaseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderTransformationServiceImpl implements OrderTransformationService {

    @Override
    public PurchaseOrder transformOrder(Order order) {
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

        Products products = new Products();
        products.setProduct(productList);
        purchaseOrder.setProducts(products);

        Payment payment = new Payment();
        payment.setCustomerId(order.getBilling().getCustomerId());
        payment.setAmount(order.getBilling().getTotalAmount());
        payment.setStatus(booleanToPaidStatus(order.getBilling().isPaid())); // Reusing existing method
        purchaseOrder.setPayment(payment);


        return purchaseOrder;
    }

    private String booleanToPaidStatus(boolean paid) {
       return paid ? "Paid" : "Unpaid";
    }
}