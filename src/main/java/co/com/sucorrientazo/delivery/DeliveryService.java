package co.com.sucorrientazo.delivery;

import co.com.sucorrientazo.delivery.dto.DronInput;
import co.com.sucorrientazo.delivery.dto.DronOutput;

import java.util.List;

public interface DeliveryService {

    List<DronOutput> deliver(final List<DronInput> inputs);

}
