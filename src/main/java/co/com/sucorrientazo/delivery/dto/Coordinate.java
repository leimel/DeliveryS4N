package co.com.sucorrientazo.delivery.dto;

import co.com.sucorrientazo.delivery.enums.CardinalPoint;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void goForward(CardinalPoint cardinalPoint) {
        switch (cardinalPoint) {
            case NORTE:
                this.setY(this.getY() + 1);
                break;
            case ORIENTE:
                this.setX(this.getX() + 1);
                break;
            case SUR:
                this.setY(this.getY() - 1);
                break;
            case OCCIDENTE:
                this.setX(this.getX() - 1);
                break;
        }
    }
}
