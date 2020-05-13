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
    private static final Logger LOGGER = Logger.getLogger("FileUtil");

    private FileUtil(){}

    public static List<String> readFile(final String fileName) {
        String path = getPath("routesin")+ "\\" + fileName;
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed readind file: " + path);
            return new ArrayList<>();
        }
    }

    public static void createAndWriteFile(final String fileName, List<String> lines) {
        String path = getPath("routesout") + "\\" + fileName;
        try {
            Path pathFile = Files.createFile(Paths.get(path));
            byte[] bytes = String.join("\n", lines).getBytes();
            Files.write(pathFile, bytes);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed writing file: "+ path);
        }
    }

    private static String getPath(String resourceName){
        return System.getProperty("user.dir") + "\\files\\" + resourceName;
    }
}
