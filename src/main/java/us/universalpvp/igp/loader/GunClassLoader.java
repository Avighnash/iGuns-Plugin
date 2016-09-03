package us.universalpvp.igp.loader;

import org.bukkit.plugin.InvalidDescriptionException;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by avigh on 9/2/2016.
 */
public class GunClassLoader extends ClassLoader {

    private final Map<String, Class<?>> classes = new HashMap<>();
    private GunDescriptionFile descriptionFile;
    private GunPlugin plugin;


    public GunClassLoader(JarFile jar, String path) throws MalformedURLException {
        Enumeration<JarEntry> entries = jar.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();


        }
        try {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(descriptionFile.getMain(), true, this);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            Class<? extends GunPlugin> plugin = null;
            try {
                plugin = clazz.asSubclass(GunPlugin.class);
            } catch (ClassCastException ex) {
                ex.printStackTrace();
            }

            this.plugin = plugin.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    public Map<String, Class<?>> getClasses() {
        return classes;
    }

    public GunDescriptionFile getPluginDescription(File file) throws InvalidDescriptionException {
        JarFile jar = null;
        InputStream stream = null;

        try {
            jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("guns.yml");

            if (entry == null) {
                throw new InvalidDescriptionException(new FileNotFoundException("Jar does not contain guns.yml!"));
            }

            stream = jar.getInputStream(entry);

            return null;

        } catch (IOException | YAMLException ex) {
            throw new InvalidDescriptionException(ex);
        } finally {
            if (jar != null) {
                try {
                    jar.close();
                } catch (IOException e) {
                    //do nothing
                }
            }

            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    //do nothing
                }
            }
        }
    }

    public Class<?> getClassByName(final String name) {
        Class<?> cachedClass = classes.get(name);

        if (cachedClass != null)
            return cachedClass;
        else {
            try {
                cachedClass = this.findClass(name);
            } catch (ClassNotFoundException e) {
                //do nothing
            }

            if (cachedClass != null) {
                return cachedClass;
            }
        }

        return null;
    }
}
