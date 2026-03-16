import java.util.HashMap;
import java.util.Map;

public class HashMapFactory implements MapFactory {
    @Override
    public Map<String, String> crearMapa() {
        return new HashMap<>();
    }
}
