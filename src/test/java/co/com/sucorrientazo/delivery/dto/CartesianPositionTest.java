package co.com.sucorrientazo.delivery.dto;

import co.com.sucorrientazo.delivery.enums.CardinalPoint;
import org.junit.Assert;
import org.junit.Test;

public class CartesianPositionTest {

    @Test
    public void shouldConvertToString(){
        CartesianPosition cartesianPosition = new CartesianPosition(new Coordinate(-2, 1), CardinalPoint.OCCIDENTE);
        Assert.assertEquals("(-2, 1) dirección Occidente", cartesianPosition.toString());
    }

    @Test
    public void shouldMoveLeft(){
        CartesianPosition cartesianPosition = new CartesianPosition(new Coordinate(-2, 1), CardinalPoint.OCCIDENTE);
        cartesianPosition.getCoordinate().goForward(cartesianPosition.getCardinalPoint());
        Assert.assertEquals(-3, cartesianPosition.getCoordinate().getX());
        Assert.assertEquals(1, cartesianPosition.getCoordinate().getY());
        Assert.assertEquals(CardinalPoint.OCCIDENTE, cartesianPosition.getCardinalPoint());
    }

    @Test
    public void shouldMoveRigth(){
        CartesianPosition cartesianPosition = new CartesianPosition(new Coordinate(-2, 1), CardinalPoint.ORIENTE);
        cartesianPosition.getCoordinate().goForward(cartesianPosition.getCardinalPoint());
        Assert.assertEquals(-1, cartesianPosition.getCoordinate().getX());
        Assert.assertEquals(1, cartesianPosition.getCoordinate().getY());
        Assert.assertEquals(CardinalPoint.ORIENTE, cartesianPosition.getCardinalPoint());
    }

    @Test
    public void shouldMoveUp(){
        CartesianPosition cartesianPosition = new CartesianPosition(new Coordinate(-2, 1), CardinalPoint.NORTE);
        cartesianPosition.getCoordinate().goForward(cartesianPosition.getCardinalPoint());
        Assert.assertEquals(-2, cartesianPosition.getCoordinate().getX());
        Assert.assertEquals(2, cartesianPosition.getCoordinate().getY());
        Assert.assertEquals(CardinalPoint.NORTE, cartesianPosition.getCardinalPoint());
    }

    @Test
    public void shouldMoveDown(){
        CartesianPosition cartesianPosition = new CartesianPosition(new Coordinate(-2, 1), CardinalPoint.SUR);
        cartesianPosition.getCoordinate().goForward(cartesianPosition.getCardinalPoint());
        Assert.assertEquals(-2, cartesianPosition.getCoordinate().getX());
        Assert.assertEquals(0, cartesianPosition.getCoordinate().getY());
        Assert.assertEquals(CardinalPoint.SUR, cartesianPosition.getCardinalPoint());
    }

    @Test
    public void shouldTurnLeftFromNorteToOccidente(){
        CartesianPosition cartesianPosition = new CartesianPosition(new Coordinate(-2, 1), CardinalPoint.NORTE);
        cartesianPosition.turnLeft();
        Assert.assertEquals(-2, cartesianPosition.getCoordinate().getX());
        Assert.assertEquals(1, cartesianPosition.getCoordinate().getY());
        Assert.assertEquals(CardinalPoint.OCCIDENTE, cartesianPosition.getCardinalPoint());
    }

    @Test
    public void shouldTurnLeftFromSurToOriente(){
        CartesianPosition cartesianPosition = new CartesianPosition(new Coordinate(-2, 1), CardinalPoint.SUR);
        cartesianPosition.turnLeft();
        Assert.assertEquals(-2, cartesianPosition.getCoordinate().getX());
        Assert.assertEquals(1, cartesianPosition.getCoordinate().getY());
        Assert.assertEquals(CardinalPoint.ORIENTE, cartesianPosition.getCardinalPoint());
    }

    @Test
    public void shouldTurnRigthFromSurToOccidente(){
        CartesianPosition cartesianPosition = new CartesianPosition(new Coordinate(-2, 1), CardinalPoint.SUR);
        cartesianPosition.turnRigth();
        Assert.assertEquals(-2, cartesianPosition.getCoordinate().getX());
        Assert.assertEquals(1, cartesianPosition.getCoordinate().getY());
        Assert.assertEquals(CardinalPoint.OCCIDENTE, cartesianPosition.getCardinalPoint());
    }

    @Test
    public void shouldTurnRigthFromOccidenteToNorte(){
        CartesianPosition cartesianPosition = new CartesianPosition(new Coordinate(-2, 1), CardinalPoint.OCCIDENTE);
        cartesianPosition.turnRigth();
        Assert.assertEquals(-2, cartesianPosition.getCoordinate().getX());
        Assert.assertEquals(1, cartesianPosition.getCoordinate().getY());
        Assert.assertEquals(CardinalPoint.NORTE, cartesianPosition.getCardinalPoint());
    }
}
