package co.com.sucorrientazo.delivery.services;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FileInputReaderServiceTest {

    FileInputReaderService fileInputReaderService = new FileInputReaderService();

    @Test
    public void shouldReadFileAndReturnListLines() {
        List<String> lines = fileInputReaderService.read(1);

        Assert.assertNotNull(lines);
        Assert.assertEquals(3, lines.size());
        Assert.assertEquals("AAAAIAA", lines.get(0));
        Assert.assertEquals("DDDAIAD", lines.get(1));
        Assert.assertEquals("AAIADAD", lines.get(2));
    }
}
