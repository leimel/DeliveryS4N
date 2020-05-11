package co.com.sucorrientazo.delivery.services;

import co.com.sucorrientazo.delivery.InputReaderService;
import co.com.sucorrientazo.delivery.dto.DronInput;
import co.com.sucorrientazo.delivery.utils.FileUtil;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileInputReaderService implements InputReaderService {
    private static final Logger LOGGER = Logger.getLogger("co.com.sucorrientazo.delivery.services.FileInputReaderService");

    public List<DronInput> read(Integer maxNumberDrones) {

        return IntStream.rangeClosed(1, maxNumberDrones)
                .mapToObj(idx -> {
                    LOGGER.log(Level.INFO, "Writing id dron: " + idx);
                    String fullPath = String.format("C:\\DeliveryS4N\\files\\routesin\\in%02d.txt", idx);
                    List<String> routes = FileUtil.readFile(fullPath);
                    return new DronInput(idx, routes);
                })
               .collect(Collectors.toList())
                ;
    }
}
