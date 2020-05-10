package co.com.sucorrientazo.delivery.services;

import co.com.sucorrientazo.delivery.utils.FileUtil;
import co.com.sucorrientazo.delivery.InputReaderService;

import java.util.List;

public class FileInputReaderService implements InputReaderService {

    @Override
    public List<String> read(final Integer dronNumber) {

        String fullPath = String.format("C:\\DeliveryS4N\\files\\routesin\\in%02d.txt", dronNumber);
        return FileUtil.readFile(fullPath);
    }
}
