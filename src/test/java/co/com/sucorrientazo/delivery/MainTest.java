package co.com.sucorrientazo.delivery;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class MainTest {

    @Test
    public void shouldExecuteProcess() {
        Main.main();

        File dir = new File(System.getProperty("user.dir") + "\\files\\routesout");
        Assert.assertTrue(0 < dir.listFiles().length);
    }

    @AfterClass
    public static void cleanFiles() {

        File dir = new File(System.getProperty("user.dir") + "\\files\\routesout");

        File[] files = dir.listFiles();
        for(File file : files)file.delete();
    }

}
