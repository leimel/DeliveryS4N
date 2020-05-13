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
import co.com.sucorrientazo.delivery.services.servicelocator.ServiceLocator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger("Main");

    public static void main(String... args) {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();
        initializeAndRegisterServices(serviceLocator);

        LOGGER.log(Level.INFO,"Start deliveries..........................................");

        launchDelivery(serviceLocator);
    }

    public static void launchDelivery(ServiceLocator serviceLocator){

        InputReaderService inputReaderService = serviceLocator.getService(InputReaderService.class, "fileInputReaderService");
        DeliveryService deliveryService = serviceLocator.getService(DeliveryService.class, "dronDeliveryService");
        OutputWriterService outputWriterService = serviceLocator.getService(OutputWriterService.class, "fileOutputWriterService");

        List<DronInput> routes = inputReaderService.read();
        List<DronOutput> finalPositions = deliveryService.deliver(routes);
        outputWriterService.write(finalPositions);

        LOGGER.log(Level.INFO,"Finished deliveries..........................................");
    }

    private static void initializeAndRegisterServices(ServiceLocator serviceLocator) {
        LOGGER.log(Level.INFO, "Initializing Service Locator");
        SystemProperties systemProperties = SystemProperties.getInstance();
        DeliveryDronRunner deliveryDronRunner = new DeliveryDronRunner(systemProperties);

        DeliveryService dronDeliveryService = new DronDeliveryService(deliveryDronRunner);
        InputReaderService fileInputReaderService = new FileInputReaderService(systemProperties);
        OutputWriterService fileOutputWriterService = new FileOutputWriterService();

        serviceLocator.registerService(DeliveryService.class, "dronDeliveryService", dronDeliveryService);
        serviceLocator.registerService(InputReaderService.class, "fileInputReaderService", fileInputReaderService);
        serviceLocator.registerService(OutputWriterService.class, "fileOutputWriterService", fileOutputWriterService);
    }

}
