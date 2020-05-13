package co.com.sucorrientazo.delivery.services.impl;

import co.com.sucorrientazo.delivery.SystemProperties;
import co.com.sucorrientazo.delivery.dto.DronInput;
import co.com.sucorrientazo.delivery.services.InputReaderService;
import co.com.sucorrientazo.delivery.utils.FileUtil;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileInputReaderService implements InputReaderService {
    private static final Logger LOGGER = Logger.getLogger("FileInputReaderService");

    private SystemProperties systemProperties;

    public FileInputReaderService(SystemProperties systemProperties) {
        this.systemProperties = systemProperties;
    }

    @Override
    public List<DronInput> read() {

        return IntStream.rangeClosed(1, systemProperties.getTotalDrons())
                .parallel()
                .mapToObj(idx -> {
                    LOGGER.log(Level.INFO, "Reading id dron: " + idx);
                    String fileName = String.format("in%02d.txt", idx);
                    List<String> routes = FileUtil.readFile(fileName);
                    return new DronInput(idx, routes);
                })
                .collect(Collectors.toList())
                ;
    }
}
