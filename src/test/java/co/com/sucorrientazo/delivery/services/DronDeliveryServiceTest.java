package co.com.sucorrientazo.delivery.services;

import co.com.sucorrientazo.delivery.dto.DronInput;
import co.com.sucorrientazo.delivery.dto.DronOutput;
import co.com.sucorrientazo.delivery.runner.DeliveryDronRunner;
import co.com.sucorrientazo.delivery.services.impl.DronDeliveryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DronDeliveryServiceTest {

    @InjectMocks
    private DronDeliveryService dronDeliveryService;

    @Mock
    private DeliveryDronRunner deliveryDronRunner;

    @Test
    public void shouldExecuteDelivery(){
        when(deliveryDronRunner.execute(any(DronInput.class))).thenReturn(new DronOutput(1, null));
        List<DronOutput> outputs = dronDeliveryService.deliver(List.of(new DronInput(1, null), new DronInput(2, null)));

        Assert.assertNotNull(outputs);
        Assert.assertEquals(2, outputs.size());

        verify(deliveryDronRunner, times(2)).execute(any());
    }

    @Test
    public void shouldNotExecuteDeliveryEmptyInputList(){
        List<DronOutput> outputs = dronDeliveryService.deliver(Collections.emptyList());

        Assert.assertNotNull(outputs);
        Assert.assertEquals(0, outputs.size());

        verifyZeroInteractions(deliveryDronRunner);
    }

    @Test
    public void shouldNotExecuteDeliveryNullInputList(){
        List<DronOutput> outputs = dronDeliveryService.deliver(Collections.emptyList());

        Assert.assertNotNull(outputs);
        Assert.assertEquals(0, outputs.size());

        verifyZeroInteractions(deliveryDronRunner);
    }
}
