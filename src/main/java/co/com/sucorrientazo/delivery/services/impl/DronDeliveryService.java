package co.com.sucorrientazo.delivery.services.impl;

import co.com.sucorrientazo.delivery.dto.DronInput;
import co.com.sucorrientazo.delivery.dto.DronOutput;
import co.com.sucorrientazo.delivery.exceptions.DeliveryException;
import co.com.sucorrientazo.delivery.runner.DeliveryDronRunner;
import co.com.sucorrientazo.delivery.services.DeliveryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
        return Optional.ofNullable(inputs)
                .orElse(new ArrayList<>())
                .parallelStream()
                .map(this::execute)
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                ;
    }

    private DronOutput execute(DronInput input){
        LOGGER.log(Level.INFO, "Delivering Dron: "+ input.getId() + "----> " + Thread.currentThread().getName());
        try {
            return deliveryDronRunner.execute(input);
        }catch (DeliveryException ex){
            LOGGER.log(Level.SEVERE, "It was not possible to execute drone deliveries: {0}", ex.getDetail());
            return null;
        }
    }
}
