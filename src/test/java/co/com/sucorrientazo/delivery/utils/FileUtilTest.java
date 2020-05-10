package co.com.sucorrientazo.delivery.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtilTest {

    @Test
    public void shouldCreateAnWriteFile() throws IOException {
        List<String> lines = List.of("(-2, 4) dirección Occidente", "(-3, 3) dirección Sur", "(-4, 2) dirección Oriente");
        String path = "C:\\DeliveryS4N\\src\\test\\resources\\routesout\\out01.txt";
        FileUtil.createAndWriteFile(path, lines);

        Path pathFile = Paths.get(path);
        List<String> linesCreated = Files.readAllLines(pathFile);

        Assert.assertNotNull(lines);
        Assert.assertEquals(3, lines.size());
        Assert.assertEquals("(-2, 4) direcci\u00f3n Occidente", linesCreated.get(0));
        Assert.assertEquals("(-3, 3) direcci\u00f3n Sur", linesCreated.get(1));
        Assert.assertEquals("(-4, 2) direcci\u00f3n Oriente", linesCreated.get(2));

        Files.delete(pathFile);
    }

    @Test
    public void shouldReadFile(){
        String path = "C:\\DeliveryS4N\\src\\test\\resources\\routesin\\in01.txt";
        List<String> lines = FileUtil.readFile(path);

        Assert.assertNotNull(lines);
        Assert.assertEquals(3, lines.size());
        Assert.assertEquals("AAAAIAA", lines.get(0));
        Assert.assertEquals("DDDAIAD", lines.get(1));
        Assert.assertEquals("AAIADAD", lines.get(2));
    }

    @Test
    public void shouldReturnEmptyListByCatchIOException(){
        String path = "C:\\DeliveryS4N\\src\\test\\resources\\routesin\\out02.txt";
        List<String> lines = FileUtil.readFile(path);

        Assert.assertNotNull(lines);
        Assert.assertTrue(lines.isEmpty());
    }
}
