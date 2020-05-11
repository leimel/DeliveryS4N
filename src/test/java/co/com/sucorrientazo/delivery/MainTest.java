package co.com.sucorrientazo.delivery;

import org.junit.AfterClass;
import org.junit.Test;

import java.io.File;

public class MainTest {

    Main main = new Main();

    @Test
    public void shouldExecuteProcess(){
       main.main();
    }

    @AfterClass
    public static void clenFiles(){
       File dir = new File("C:\\DeliveryS4N\\files\\routesout");

        File[] files = dir.listFiles();
        for(File file : files)file.delete();
    }
}
