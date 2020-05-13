package co.com.sucorrientazo.delivery;

import org.junit.Assert;
import org.junit.Test;

public class SystemPropertiesTest {

    @Test
    public void shouldCreateSystemPropertiesIntance(){
        SystemProperties instance = SystemProperties.getInstance();

        Assert.assertNotNull(instance);
        Assert.assertEquals(20, instance.getTotalDrons().intValue());
        Assert.assertEquals(3, instance.getDronCapacity().intValue());
        Assert.assertEquals(10, instance.getMaxDistance().intValue());
    }

    @Test
    public void shouldCreateOnlyOneInstance(){
        SystemProperties instance = SystemProperties.getInstance();
        SystemProperties instance2 = SystemProperties.getInstance();
        SystemProperties instance3 = SystemProperties.getInstance();


        Assert.assertSame(instance, instance2);
        Assert.assertSame(instance, instance3);

    }

}
