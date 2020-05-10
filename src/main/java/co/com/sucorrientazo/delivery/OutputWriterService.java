package co.com.sucorrientazo.delivery;

import java.util.List;

public interface OutputWriterService {

    void writeFinalPositions(final Integer dronNumber, final List<String> positions);
}
