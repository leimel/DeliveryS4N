package co.com.sucorrientazo.delivery.dto;

import java.util.List;

public class DronOutput {

    private Integer id;
    private List<String> finalPositions;

    public DronOutput(Integer id, List<String> finalPositions) {
        this.id = id;
        this.finalPositions = finalPositions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getFinalPositions() {
        return finalPositions;
    }

    public void setFinalPositions(List<String> finalPositions) {
        this.finalPositions = finalPositions;
    }
}
