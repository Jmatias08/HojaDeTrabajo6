import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapFactory implements MapFactory {
    @Override
    public Map<String, String> crearMapa() {
        return new LinkedHashMap<>();
    }
}
