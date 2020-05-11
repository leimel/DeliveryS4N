package co.com.sucorrientazo.delivery;

import co.com.sucorrientazo.delivery.dto.DronInput;

import java.util.List;

public interface InputReaderService {

    List<DronInput> read(Integer maxNumberDrones);
}
