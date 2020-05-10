package co.com.sucorrientazo.delivery.services;

import co.com.sucorrientazo.delivery.utils.FileUtil;
import co.com.sucorrientazo.delivery.OutputWriterService;

import java.util.List;

public class FileOutputWriterService implements OutputWriterService {

    @Override
    public void writeFinalPositions(Integer dronNumber, List<String> positions) {
        String fullPath = String.format("C:\\DeliveryS4N\\files\\routesout\\out%02d.txt", dronNumber);
        FileUtil.createAndWriteFile(fullPath, positions);
    }
}
