package co.com.sucorrientazo.delivery.enums;

public enum CardinalPoint {
    NORTE(0,"Norte"),
    ORIENTE(1, "Oriente"),
    SUR(2, "Sur"),
    OCCIDENTE(3, "Occidente");

    private String name;
    private Integer index;

    CardinalPoint(Integer index, String name) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public Integer getIndex() {
        return index;
    }

}
