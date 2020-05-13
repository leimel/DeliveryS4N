package co.com.sucorrientazo.delivery.runner;

import co.com.sucorrientazo.delivery.SystemProperties;
import co.com.sucorrientazo.delivery.dto.DronInput;
import co.com.sucorrientazo.delivery.dto.DronOutput;
import co.com.sucorrientazo.delivery.exceptions.DeliveryException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryDronRunnerTest {

    @InjectMocks
    private DeliveryDronRunner deliveryDronRunner;
    @Mock
    private SystemProperties systemProperties;


    @Test
    public void shouldCalculateFinalPositionsOneRoundThreeDeliveries(){
        when(systemProperties.getDronCapacity()).thenReturn(3);
        when(systemProperties.getMaxDistance()).thenReturn(10);
        List<String> routes = List.of("AAAAIAA", "DDDAIAD", "AAIADADX");
        DronInput dronInput = new DronInput(1, routes);
        DronOutput dronOutput = deliveryDronRunner.execute(dronInput);

        Assert.assertNotNull(dronOutput);
        Assert.assertEquals(dronInput.getId(), dronOutput.getId());
        Assert.assertEquals(3, dronOutput.getFinalPositions().size());
        Assert.assertEquals("(-2, 4) dirección Occidente", dronOutput.getFinalPositions().get(0));
        Assert.assertEquals("(-1, 3) dirección Sur", dronOutput.getFinalPositions().get(1));
        Assert.assertEquals("(0, 0) dirección Occidente", dronOutput.getFinalPositions().get(2));
    }

    @Test
    public void shouldCalculateFinalPositionsTwoRoundsFiveDeliveries(){
        when(systemProperties.getDronCapacity()).thenReturn(3);
        when(systemProperties.getMaxDistance()).thenReturn(10);
        List<String> routes = List.of("AAAAIAAD", "DDAIAD", "AAIADAD", "AAAAIAAD", "DDDAIAD");
        DronInput dronInput = new DronInput(1, routes);

        DronOutput dronOutput = deliveryDronRunner.execute(dronInput);

        Assert.assertNotNull(dronOutput);
        Assert.assertEquals(5, dronOutput.getFinalPositions().size());
        Assert.assertEquals("(-2, 4) dirección Norte", dronOutput.getFinalPositions().get(0));
        Assert.assertEquals("(-1, 3) dirección Sur", dronOutput.getFinalPositions().get(1));
        Assert.assertEquals("(0, 0) dirección Occidente", dronOutput.getFinalPositions().get(2));
        Assert.assertEquals("(-2, 4) dirección Norte", dronOutput.getFinalPositions().get(3));
        Assert.assertEquals("(-3, 3) dirección Occidente", dronOutput.getFinalPositions().get(4));
    }

    @Test
    public void shouldCalculateFinalPositionsOneRoundOneDelivery(){
        when(systemProperties.getDronCapacity()).thenReturn(3);
        when(systemProperties.getMaxDistance()).thenReturn(10);
        List<String> routes = List.of("AAAAIAA");
        DronInput dronInput = new DronInput(10, routes);

        DronOutput dronOutput = deliveryDronRunner.execute(dronInput);

        Assert.assertNotNull(dronOutput);
        Assert.assertEquals(1, dronOutput.getFinalPositions().size());
        Assert.assertEquals("(-2, 4) dirección Occidente", dronOutput.getFinalPositions().get(0));
    }

    @Test
    public void shouldReturnUndeliveredBecauseisOutOfRange(){
        when(systemProperties.getDronCapacity()).thenReturn(3);
        when(systemProperties.getMaxDistance()).thenReturn(10);
        List<String> routes = List.of("AAAAIDAADIAAAAA");
        DronInput dronInput = new DronInput(10, routes);

        DronOutput dronOutput = deliveryDronRunner.execute(dronInput);

        Assert.assertNotNull(dronOutput);
        Assert.assertEquals(1, dronOutput.getFinalPositions().size());
        Assert.assertEquals("Undelivered", dronOutput.getFinalPositions().get(0));
    }

    @Test
    public void shouldValidateNullInput(){
        try {
            deliveryDronRunner.execute(null);
        }catch (DeliveryException ex){
            Assert.assertEquals("An error occurred processing the delivery", ex.getDetail());
        }
    }

    @Test
    public void shouldValidateNullInputList(){
        try {
            deliveryDronRunner.execute(null);
        }catch (DeliveryException ex){
            Assert.assertEquals("An error occurred processing the delivery", ex.getDetail());
        }
    }

    @Test
    public void shouldValidateEmptyInputList(){
        when(systemProperties.getDronCapacity()).thenReturn(3);
        DronOutput output = deliveryDronRunner.execute(new DronInput(1, Collections.emptyList()));

        Assert.assertNotNull(output);
        Assert.assertEquals(1, output.getId().intValue());
        Assert.assertTrue(output.getFinalPositions().isEmpty());

    }
}
