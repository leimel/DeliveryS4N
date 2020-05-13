package co.com.sucorrientazo.delivery.services;

import co.com.sucorrientazo.delivery.dto.DronInput;
import co.com.sucorrientazo.delivery.SystemProperties;
import co.com.sucorrientazo.delivery.services.impl.FileInputReaderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileInputReaderServiceTest {

    @InjectMocks
    private FileInputReaderService fileInputReaderService;

    @Mock
    private SystemProperties systemProperties;

    @Test
    public void shouldReadFileAndReturnListLines() {
        when(systemProperties.getTotalDrons()).thenReturn(1);
        List<DronInput> inputs = fileInputReaderService.read();

        Assert.assertNotNull(inputs);
        Assert.assertEquals(1, inputs.size());
        Assert.assertEquals(3, inputs.get(0).getRoutes().size());
        Assert.assertEquals("AAAAIAA", inputs.get(0).getRoutes().get(0));
        Assert.assertEquals("DDDAIAD", inputs.get(0).getRoutes().get(1));
        Assert.assertEquals("AAIADAD", inputs.get(0).getRoutes().get(2));
    }
}
