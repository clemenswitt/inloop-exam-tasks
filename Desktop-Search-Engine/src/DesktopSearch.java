import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesktopSearch {
    private Map<String, ResourceType> types;
    private Index index;

    public DesktopSearch() {
        types = new HashMap<>();
        index = new Index();
    }

    public void registerType(String extension, ResourceType type) {
        if(extension == null || type == null) {
            throw new NullPointerException("Extension darf nicht null sein.");
        }
        types.put(extension, type);
    }

    public ResourceType getType(String extension) {
        return types.get(extension);
    }

    public void unregisterType(String extension) {
        if(extension == null) {
            throw new NullPointerException("Extension darf nicht null sein.");
        }
        types.remove(extension);
    }

    public void registerResource(Resource res) {
        index.add(res);
    }

    public List<Resource> processRequest(String request) {
        if(request.isEmpty()) {
            throw new IllegalArgumentException("Request darf nicht leer sein.");
        }
        return index.getResources(request);
    }
}
