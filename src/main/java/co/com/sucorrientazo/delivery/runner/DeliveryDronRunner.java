package co.com.sucorrientazo.delivery.runner;

import co.com.sucorrientazo.delivery.SystemProperties;
import co.com.sucorrientazo.delivery.dto.CartesianPosition;
import co.com.sucorrientazo.delivery.dto.Coordinate;
import co.com.sucorrientazo.delivery.dto.DronInput;
import co.com.sucorrientazo.delivery.dto.DronOutput;
import co.com.sucorrientazo.delivery.enums.CardinalPoint;
import co.com.sucorrientazo.delivery.exceptions.DeliveryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DeliveryDronRunner {
    private static final Logger LOGGER = Logger.getLogger("DeliveryDronRunner");

    private SystemProperties systemProperties;

    public DeliveryDronRunner(SystemProperties systemProperties) {
        this.systemProperties = systemProperties;
    }

    public DronOutput execute(final DronInput deliveryDron) {
        List<String> finalPositions = Optional.ofNullable(deliveryDron)
                .map(DronInput::getRoutes)
                .map(routes -> groupRoutesByDronCapacity(routes, this.systemProperties.getDronCapacity()))
                .orElseThrow(() -> new DeliveryException("An error occurred processing the delivery"))
                .stream()
                .map(routes -> processRoutePerRound(routes, this.systemProperties.getMaxDistance()))
                .flatMap(list -> Objects.nonNull(list) ? list.stream() : null)
                .collect(Collectors.toList())
                ;

        return new DronOutput(deliveryDron.getId(), finalPositions);
    }

    private List<String> processRoutePerRound(final String route, final Integer maxDistance) {
        LOGGER.log(Level.INFO, "Processing round: {0}", route);
        List<String> routes = List.of(route.split(","));

        Coordinate coordinate = new Coordinate(0, 0);
        CartesianPosition initialPosition = new CartesianPosition(coordinate, CardinalPoint.NORTE);
        List<CartesianPosition> positions = new ArrayList<>();

        IntStream.range(0, routes.size())
                .forEach(idx -> {
                    final CartesianPosition currentPosition = 0 == idx ? initialPosition : positions.get(idx - 1);
                    CartesianPosition position = processSingleRoute(routes.get(idx), currentPosition, maxDistance);
                    positions.add(position);
                });

        return positions.stream()
                .map(position -> Objects.nonNull(position) ? position.toString() : "Undelivered")
                .collect(Collectors.toList())
                ;
    }

    private CartesianPosition processSingleRoute(String singleRoute, CartesianPosition initialCartesianPosition, final Integer maxDistance) {
        CartesianPosition currentPosition = new CartesianPosition();
        currentPosition.setCardinalPoint(initialCartesianPosition.getCardinalPoint());
        Coordinate coordinate = new Coordinate(initialCartesianPosition.getCoordinate().getX(), initialCartesianPosition.getCoordinate().getY());

        IntStream.range(0, singleRoute.length())
                .forEachOrdered(idx -> {
                    switch (singleRoute.charAt(idx)) {
                        case 'A':
                            coordinate.goForward(currentPosition.getCardinalPoint());
                            break;
                        case 'I':
                            currentPosition.turnLeft();
                            break;
                        case 'D':
                            currentPosition.turnRigth();
                            break;
                        default:
                            LOGGER.log(Level.SEVERE, "Instruction not configured");
                    }
                });

        currentPosition.setCoordinate(coordinate);

        return isCoordinateInRange(coordinate, maxDistance) ? currentPosition : null;
    }

    private List<String> groupRoutesByDronCapacity(List<String> routes, Integer dronCapacity) {

        if(dronCapacity > 0) {
            return IntStream.range(0, (routes.size() + dronCapacity - 1) / dronCapacity)
                    .mapToObj(i ->
                            String.join(",", routes.subList(i * dronCapacity, Math.min(dronCapacity * (i + 1), routes.size())))
                    )
                    .collect(Collectors.toList());
        }else{
            throw new DeliveryException("the capacity of the drone is 0");
        }
    }

    private boolean isCoordinateInRange(final Coordinate coordinate, Integer maxDistance){
        return (Math.abs(coordinate.getX()) <= maxDistance) && (Math.abs(coordinate.getY()) <= maxDistance);
    }
}
