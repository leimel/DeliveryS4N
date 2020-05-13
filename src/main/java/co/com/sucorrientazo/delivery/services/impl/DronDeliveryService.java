package co.com.sucorrientazo.delivery.services.impl;

import co.com.sucorrientazo.delivery.dto.DronInput;
import co.com.sucorrientazo.delivery.dto.DronOutput;
import co.com.sucorrientazo.delivery.runner.DeliveryDronRunner;
import co.com.sucorrientazo.delivery.services.DeliveryService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DronDeliveryService implements DeliveryService {
    private static final Logger LOGGER = Logger.getLogger("DronDeliveryService");

    private DeliveryDronRunner deliveryDronRunner;

    public DronDeliveryService(DeliveryDronRunner deliveryDronRunner) {
        this.deliveryDronRunner = deliveryDronRunner;
    }

    @Override
    public List<DronOutput> deliver(final List<DronInput> inputs){
        return inputs
                .parallelStream()
                .map(input -> {
                    LOGGER.log(Level.INFO, "Delivering Dron: "+ input.getId() + "----> " + Thread.currentThread().getName());
                    return deliveryDronRunner.execute(input);
                })
                .collect(Collectors.toList())
                ;
    }
}
