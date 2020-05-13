package co.com.sucorrientazo.delivery.dto;

import java.util.List;

public class DronInput {

    private Integer id;
    private List<String> routes;

    public DronInput(Integer id, List<String> routes) {
        this.id = id;
        this.routes = routes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getRoutes() {
        return routes;
    }

    public void setRoutes(List<String> routes) {
        this.routes = routes;
    }
}
