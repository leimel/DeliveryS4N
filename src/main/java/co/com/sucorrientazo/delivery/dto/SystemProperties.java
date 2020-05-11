package co.com.sucorrientazo.delivery.dto;

public class SystemProperties {

    private Integer totalDrons;
    private Integer maxDistance;
    private Integer dronCapacity;

    public SystemProperties(Integer totalDrons, Integer maxDistance, Integer dronCapacity) {
        this.totalDrons = totalDrons;
        this.maxDistance = maxDistance;
        this.dronCapacity = dronCapacity;
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
