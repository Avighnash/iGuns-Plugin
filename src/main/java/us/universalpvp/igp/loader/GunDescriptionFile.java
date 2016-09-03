package us.universalpvp.igp.loader;

import org.yaml.snakeyaml.Yaml;
import us.universalpvp.igp.loader.exception.InvalidDescriptionException;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by avigh on 9/2/2016.
 */
public class GunDescriptionFile {

    private Yaml yaml = new Yaml();

    private String name;
    private String main;
    private String version;
    private String creator;
    private String classLoader;
    private String description;

    public GunDescriptionFile(InputStream stream) throws InvalidDescriptionException {
        loadMap(asMap(yaml.load(stream)));
    }

    public GunDescriptionFile(String name, String version, String main, String creator, String classLoader,
                              String description) {
        this.name = name.replace(' ', '_');
        this.version = version;
        this.main = main;
        this.creator = creator;
        this.classLoader = classLoader;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getMain() {
        return main;
    }

    public String getCreator() {
        return creator;
    }

    public String getVersion() {
        return version;
    }

    public String getClassLoader() {
        return classLoader;
    }

    public String getDescription() {
        return description;
    }

    private Map<?, ?> asMap(Object object) {
        if (object instanceof Map) {
            return (Map<?, ?>) object;
        }
        throw new IllegalArgumentException("Wrong parameter!");
    }

    private void loadMap(Map<?, ?> map) throws InvalidDescriptionException {
        try {
            name = map.get("name").toString();

            if (!name.matches("^[A-Za-z0-9 _.-]")) {
                throw new InvalidDescriptionException("Name contains invalid characters!");
            }

            name = name.replace(' ', '_');
        } catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "Name is null!");
        }

        try {
            version = map.get("version").toString();
        } catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "Version is null!");
        }

        try {
            main = map.get("main").toString();
        } catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "Main is null!");
        }

        if (map.get("description") != null) {
            description = map.get("description").toString();
        }

    }

}
