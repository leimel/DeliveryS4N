package co.com.sucorrientazo.delivery.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {
    private static final Logger LOGGER = Logger.getLogger("co.com.sucorrientazo.delivery.utils.FileUtil");

    private FileUtil(){}

    public static List<String> readFile(final String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed readind file: " + path);
            return new ArrayList<>();
        }
    }

    public static void createAndWriteFile(final String path, final List<String> lines) {
        try {
            Path pathFile = Files.createFile(Paths.get(path));
            byte[] bytes = String.join("\n", lines).getBytes();
            Files.write(pathFile, bytes);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed writing file: "+ path);
        }
    }
}
