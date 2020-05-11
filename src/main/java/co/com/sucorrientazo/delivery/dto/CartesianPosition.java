package co.com.sucorrientazo.delivery.dto;


import co.com.sucorrientazo.delivery.enums.CardinalPoint;

public class CartesianPosition {
    private Coordinate coordinate;
    private CardinalPoint cardinalPoint;

    public CartesianPosition(Coordinate coordinate, CardinalPoint cardinalPoint) {
        this.coordinate = new Coordinate(coordinate.getX(), coordinate.getY());
        this.cardinalPoint = cardinalPoint;
    }

    public CartesianPosition() {}

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public CardinalPoint getCardinalPoint() {
        return cardinalPoint;
    }

    public void setCardinalPoint(CardinalPoint cardinalPoint) {
        this.cardinalPoint = cardinalPoint;
    }

    @Override
    public String toString() {
        return "(" + this.coordinate.getX() + ", " + this.coordinate.getY() + ") dirección " + this.cardinalPoint.getName();
    }

    public void turnLeft() {
        this.setCardinalPoint(this.cardinalPoint.getIndex() -1 < 0 ? getFromIndex(CardinalPoint.values().length-1): getFromIndex(this.cardinalPoint.getIndex()-1));
    }

    public void turnRigth() {
       this.setCardinalPoint(this.cardinalPoint.getIndex() + 1 >= CardinalPoint.values().length ? getFromIndex(0): getFromIndex(this.cardinalPoint.getIndex() + 1));
    }

    public CardinalPoint getFromIndex(Integer index){
        return CardinalPoint.values()[index];
    }
}
