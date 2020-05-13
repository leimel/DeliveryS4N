package co.com.sucorrientazo.delivery;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SystemProperties {
    private static final Logger LOGGER = Logger.getLogger("SystemProperties");

    private Integer totalDrons;
    private Integer maxDistance;
    private Integer dronCapacity;

    private static SystemProperties instance;

    private SystemProperties(Integer totalDrons, Integer maxDistance, Integer dronCapacity) {
        this.totalDrons = totalDrons;
        this.maxDistance = maxDistance;
        this.dronCapacity = dronCapacity;
    }

    public static SystemProperties getInstance() {
        SystemProperties result = instance;
        if (result != null) {
            return result;
        }
        synchronized (SystemProperties.class) {
            if (instance == null) {
                Properties properties = readSystemProperties();

                Integer totalDrones = Integer.parseInt(properties.getProperty("total-drons", "0"));
                Integer maxDistance = Integer.parseInt(properties.getProperty("max-distance", "0"));
                Integer dronCapacity = Integer.parseInt(properties.getProperty("dron-capacity", "0"));

                instance = new SystemProperties(totalDrones, maxDistance, dronCapacity);
            }
            return instance;
        }
    }

    private static Properties readSystemProperties() {
        String systemPropertiesPath = System.getProperty("user.dir") + "\\files\\system.properties";
        try (InputStream inputStream = new FileInputStream(systemPropertiesPath)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred loading the system configuration" + e.getMessage());
        }

        return new Properties();
    }

    public Integer getTotalDrons() {
        return totalDrons;
    }

    public Integer getMaxDistance() {
        return maxDistance;
    }

    public Integer getDronCapacity() {
        return dronCapacity;
    }
}
