package org.example.DeliveryService.dg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.DeliveryService.enums.DeliveryStatus;
import org.example.DeliveryService.repository.DeliveryRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeliveryStatusUpdater {

    private final DeliveryRepository deliveryRepository;

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void deliveryStatusUpdate() {
        log.info("----------- delivery status update ------------");

        deliveryRepository.findAllByStatus(DeliveryStatus.IN_DELIVERY)
            .forEach(delivery -> delivery.setStatus(DeliveryStatus.COMPLETED));

        deliveryRepository.findAllByStatus(DeliveryStatus.REQUESTED)
            .forEach(delivery -> delivery.setStatus(DeliveryStatus.IN_DELIVERY));
    }

}
