package co.com.sucorrientazo.delivery.services;

import co.com.sucorrientazo.delivery.DeliveryService;
import co.com.sucorrientazo.delivery.dto.DronInput;
import co.com.sucorrientazo.delivery.dto.DronOutput;
import co.com.sucorrientazo.delivery.dto.SystemProperties;
import co.com.sucorrientazo.delivery.runner.DeliveryDronRunner;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DronDeliveryService implements DeliveryService {
    private static final Logger LOGGER = Logger.getLogger("co.com.sucorrientazo.delivery.services.DronDeliveryService");

    private DeliveryDronRunner deliveryDronRunner;
    private SystemProperties systemProperties;

    public DronDeliveryService(SystemProperties systemProperties, DeliveryDronRunner deliveryDronRunner) {
        this.deliveryDronRunner = deliveryDronRunner;
        this.systemProperties = systemProperties;
    }

    public List<DronOutput> deliver(final List<DronInput> inputs){
        return inputs.stream()
                .parallel()
                .map(input -> {
                    LOGGER.log(Level.INFO, "Delivering Dron: "+ input.getId());
                    return deliveryDronRunner.execute(input, systemProperties);
                })
                .collect(Collectors.toList())
                ;
    }
}
