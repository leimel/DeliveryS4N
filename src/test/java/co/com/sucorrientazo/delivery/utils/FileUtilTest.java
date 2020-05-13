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
        FileUtil.createAndWriteFile("out1999.txt", lines);


        Path pathFile = Paths.get(getPath("routesout\\out1999.txt"));
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
        List<String> lines = FileUtil.readFile("in01.txt");

        Assert.assertNotNull(lines);
        Assert.assertEquals(3, lines.size());
        Assert.assertEquals("AAAAIAA", lines.get(0));
        Assert.assertEquals("DDDAIAD", lines.get(1));
        Assert.assertEquals("AAIADAD", lines.get(2));
    }

    @Test
    public void shouldReturnEmptyListByCatchIOException(){
        List<String> lines = FileUtil.readFile("out02.txt");

        Assert.assertNotNull(lines);
        Assert.assertTrue(lines.isEmpty());
    }

    private static String getPath(String resourceName){
        return System.getProperty("user.dir") + "\\files\\" + resourceName;
    }
}
