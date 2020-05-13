package co.com.sucorrientazo.delivery.services;

import co.com.sucorrientazo.delivery.dto.DronInput;
import co.com.sucorrientazo.delivery.dto.DronOutput;
import co.com.sucorrientazo.delivery.SystemProperties;
import co.com.sucorrientazo.delivery.runner.DeliveryDronRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DeliveryDronRunnerTest {

    private DeliveryDronRunner deliveryDronRunner;

    @Before
    public void setUp(){
        deliveryDronRunner = new DeliveryDronRunner(SystemProperties.getInstance());
    }

    @Test
    public void shouldCalculateFinalPositionsOneRoundThreeDeliveries(){
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
        List<String> routes = List.of("AAAAIAA");
        DronInput dronInput = new DronInput(10, routes);

        DronOutput dronOutput = deliveryDronRunner.execute(dronInput);

        Assert.assertNotNull(dronOutput);
        Assert.assertEquals(1, dronOutput.getFinalPositions().size());
        Assert.assertEquals("(-2, 4) dirección Occidente", dronOutput.getFinalPositions().get(0));
    }
}
