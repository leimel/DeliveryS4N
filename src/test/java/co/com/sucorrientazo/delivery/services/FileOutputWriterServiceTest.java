package co.com.sucorrientazo.delivery.services;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileOutputWriterServiceTest {

    FileOutputWriterService fileOutputWriterService = new FileOutputWriterService();

    @Test
    public void shouldCreateAndWriteFile() throws IOException {
        String path = "C:\\DeliveryS4N\\files\\routesout\\out20.txt";
        List<String> positions = List.of("(-2, 4) dirección Occidente", "(-3, 3) dirección Sur", "(-4, 2) dirección Oriente");

        fileOutputWriterService.writeFinalPositions(20, positions);

        Path pathFile = Paths.get(path);
        List<String> lines = Files.readAllLines(pathFile);

        Assert.assertNotNull(lines);
        Assert.assertEquals(3, lines.size());
        Assert.assertEquals("(-2, 4) direcci\u00f3n Occidente", lines.get(0));
        Assert.assertEquals("(-3, 3) direcci\u00f3n Sur", lines.get(1));
        Assert.assertEquals("(-4, 2) direcci\u00f3n Oriente", lines.get(2));

        Files.delete(pathFile);
    }
}
