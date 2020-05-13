package co.com.sucorrientazo.delivery.services.impl;

import co.com.sucorrientazo.delivery.dto.DronOutput;
import co.com.sucorrientazo.delivery.services.OutputWriterService;
import co.com.sucorrientazo.delivery.utils.FileUtil;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileOutputWriterService implements OutputWriterService {
    private static final Logger LOGGER = Logger.getLogger("FileOutputWriterService");

    @Override
    public void write(List<DronOutput> positions) {
        positions
                .forEach(output -> {
                    LOGGER.log(Level.INFO, "Writing id dron: " + output.getId());
                    String fileName = String.format("out%02d.txt", output.getId());
                    FileUtil.createAndWriteFile(fileName, output.getFinalPositions());
                });
    }
}
