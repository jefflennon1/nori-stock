package com.noriservices.noristock.kafka;


import com.noriservices.noristock.kafka.dto.OrderItemEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StockUpdatedProducerService {

    private static final String TOPIC = "inventory-updated";

    @Autowired
    private KafkaTemplate<String, OrderItemEventDTO> kafkaTemplate;

    public void publishInventoryUpdated(OrderItemEventDTO event){
       kafkaTemplate.send(TOPIC, event);
    }
}
