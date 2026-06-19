package com.noriservices.noristock.kafka;

import com.noriservices.noristock.kafka.dto.OrderConfirmedEventDTO;
import com.noriservices.noristock.kafka.dto.OrderItemEventDTO;
import com.noriservices.noristock.model.ProductModel;
import com.noriservices.noristock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConfirmedListenerService  {

    @Autowired
    private ProductService productService;

    @KafkaListener(topics = "order-confirmed")
    public void processOrderConfirmed(OrderConfirmedEventDTO event){

         for(OrderItemEventDTO item : event.items()){
              ProductModel product = productService.findById(item.productId());
              product.setQuantity(product.getQuantity() - item.quantity());
              productService.save(product);
         }
    }
}
