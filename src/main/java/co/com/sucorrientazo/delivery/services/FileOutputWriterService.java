package co.com.sucorrientazo.delivery.services;

import co.com.sucorrientazo.delivery.OutputWriterService;
import co.com.sucorrientazo.delivery.dto.DronOutput;
import co.com.sucorrientazo.delivery.utils.FileUtil;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileOutputWriterService implements OutputWriterService {
    private static final Logger LOGGER = Logger.getLogger("co.com.sucorrientazo.delivery.services.FileOutputWriterService");

    @Override
    public void write(List<DronOutput> positions) {
        positions
                .forEach(output -> {
                    LOGGER.log(Level.INFO, "Writing id dron: " + output.getId());
                    String fullPath = String.format("C:\\DeliveryS4N\\files\\routesout\\out%02d.txt", output.getId());
                    FileUtil.createAndWriteFile(fullPath, output.getFinalPositions());
                });
    }
}
