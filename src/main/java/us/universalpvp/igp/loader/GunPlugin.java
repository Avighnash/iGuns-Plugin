package us.universalpvp.igp.loader;

import us.universalpvp.igp.api.IGunsAPI;
import us.universalpvp.igp.manager.Gun;

/**
 * Created by avigh on 9/2/2016.
 */
public abstract class GunPlugin {

    private ClassLoader classLoader;
    private GunDescriptionFile descriptionFile;

    public GunPlugin() {

    }

    void onInit() {

    }

    void onDisable() {

    }

    void registerGun(Gun gun) {
        IGunsAPI.getAPI().getGunManager().registerGun(gun);
    }

    void unregisterGun(Gun gun) {
        IGunsAPI.getAPI().getGunManager().unregisterGun(gun);
    }


    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public GunDescriptionFile getDescriptionFile() {
        return descriptionFile;
    }
}
