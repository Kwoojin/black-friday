package org.example.DeliveryService.dg;

import blackfriday.protobuf.EdaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.DeliveryService.entity.DeliveryEntity;
import org.example.DeliveryService.enums.DeliveryStatus;
import org.example.DeliveryService.repository.DeliveryRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeliveryStatusUpdater {

    private final DeliveryRepository deliveryRepository;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void deliveryStatusUpdate() {
        log.info("----------- delivery status update ------------");

        deliveryRepository.findAllByStatus(DeliveryStatus.IN_DELIVERY)
            .forEach(delivery -> {
                delivery.setStatus(DeliveryStatus.COMPLETED);
                publishStatusChange(delivery);
            });

        deliveryRepository.findAllByStatus(DeliveryStatus.REQUESTED)
            .forEach(delivery -> {
                delivery.setStatus(DeliveryStatus.IN_DELIVERY);
                publishStatusChange(delivery);
            });
    }

    private void publishStatusChange(DeliveryEntity delivery) {
        // 배송 상태 publish
        EdaMessage.DeliveryStatusUpdateV1 deliveryStatusMessage = EdaMessage.DeliveryStatusUpdateV1.newBuilder()
                .setOrderId(delivery.getOrderId())
                .setDeliveryId(delivery.getDeliveryId())
                .setDeliveryStatus(delivery.getStatus().toString())
                .build();

        kafkaTemplate.send("delivery_status_update", deliveryStatusMessage.toByteArray());
    }

}
