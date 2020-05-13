package co.com.sucorrientazo.delivery;

import co.com.sucorrientazo.delivery.dto.DronInput;
import co.com.sucorrientazo.delivery.dto.DronOutput;
import co.com.sucorrientazo.delivery.runner.DeliveryDronRunner;
import co.com.sucorrientazo.delivery.services.DeliveryService;
import co.com.sucorrientazo.delivery.services.InputReaderService;
import co.com.sucorrientazo.delivery.services.OutputWriterService;
import co.com.sucorrientazo.delivery.services.impl.DronDeliveryService;
import co.com.sucorrientazo.delivery.services.impl.FileInputReaderService;
import co.com.sucorrientazo.delivery.services.impl.FileOutputWriterService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger("Main");

    public static void main(String... args) {
        LOGGER.log(Level.INFO,"Start deliveries..........................................");
        launchDelivery();
    }


    public static void launchDelivery(){
        SystemProperties systemProperties = SystemProperties.getInstance();

        InputReaderService inputReaderService = new FileInputReaderService(systemProperties);
        List<DronInput> routesPerDron = inputReaderService.read();

        DeliveryService deliveryService = new DronDeliveryService(new DeliveryDronRunner(systemProperties));//new DeliveryServiceFactory().getDeliveryService("Dron");
        List<DronOutput> finalPositions = deliveryService.deliver(routesPerDron);

        OutputWriterService outputWriterService = new FileOutputWriterService();
        outputWriterService.write(finalPositions);

        LOGGER.log(Level.INFO,"Finished deliveries..........................................");
    }

}
