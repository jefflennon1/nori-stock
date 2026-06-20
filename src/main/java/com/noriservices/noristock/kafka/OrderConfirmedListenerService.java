package com.noriservices.noristock.kafka;

import com.noriservices.noristock.kafka.dto.OrderConfirmedEventDTO;
import com.noriservices.noristock.kafka.dto.OrderItemEventDTO;
import com.noriservices.noristock.model.MovementType;
import com.noriservices.noristock.model.ProductModel;
import com.noriservices.noristock.model.StockMovementModel;
import com.noriservices.noristock.service.ProductService;
import com.noriservices.noristock.service.StockMovementService;
import com.noriservices.noristock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderConfirmedListenerService  {

    @Autowired
    private ProductService productService;

    @Autowired
    private StockMovementService stockMovementService;

    @Autowired
    private UserService userService;

    @Transactional
    @KafkaListener(topics = "order-confirmed")
    public void processOrderConfirmed(OrderConfirmedEventDTO event){
        if(stockMovementService.isStockMovementPresent(event.orderId())){
            return;
        }

         for(OrderItemEventDTO item : event.items()){
              ProductModel product = productService.findById(item.productId());
              product.setQuantity(product.getQuantity() - item.quantity());
              productService.save(product);

             StockMovementModel movement = new StockMovementModel();
             movement.setProduct(product);
             movement.setSector(product.getSector());
             movement.setUser(userService.getSystemUser());
             movement.setType(MovementType.OUTBOUND);
             movement.setQuantity(item.quantity());
             movement.setOrderId(event.orderId());
             movement.setReason("Sale confirmed, order: " + event.orderId());

             stockMovementService.save(movement);
         }
    }
}
