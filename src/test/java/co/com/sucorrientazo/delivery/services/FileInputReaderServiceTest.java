package co.com.sucorrientazo.delivery.services;

import co.com.sucorrientazo.delivery.dto.DronInput;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FileInputReaderServiceTest {

    FileInputReaderService fileInputReaderService = new FileInputReaderService();

    @Test
    public void shouldReadFileAndReturnListLines() {
        List<DronInput> inputs = fileInputReaderService.read(1);

        Assert.assertNotNull(inputs);
        Assert.assertEquals(1, inputs.size());
        Assert.assertEquals(3, inputs.get(0).getRoutes().size());
        Assert.assertEquals("AAAAIAA", inputs.get(0).getRoutes().get(0));
        Assert.assertEquals("DDDAIAD", inputs.get(0).getRoutes().get(1));
        Assert.assertEquals("AAIADAD", inputs.get(0).getRoutes().get(2));
    }
}
