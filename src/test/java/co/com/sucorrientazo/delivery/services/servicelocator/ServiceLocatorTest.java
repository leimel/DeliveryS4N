package co.com.sucorrientazo.delivery.services.servicelocator;

import co.com.sucorrientazo.delivery.services.InputReaderService;
import co.com.sucorrientazo.delivery.services.impl.FileInputReaderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ServiceLocatorTest {

    private ServiceLocator serviceLocator;

    @Mock
    private FileInputReaderService fileInputReaderService;

    @Before
    public void setUp(){
        serviceLocator = ServiceLocator.getInstance();
    }

    @Test
    public void shouldRegisterServices(){
        serviceLocator.registerService(InputReaderService.class, "MyReader", fileInputReaderService);

        Assert.assertSame(fileInputReaderService, serviceLocator.getService(InputReaderService.class, "MyReader"));

    }
}
