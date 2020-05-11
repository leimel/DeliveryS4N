package co.com.sucorrientazo.delivery;

import co.com.sucorrientazo.delivery.dto.DronOutput;

import java.util.List;

public interface OutputWriterService {

    void write(final List<DronOutput> positions);
}
