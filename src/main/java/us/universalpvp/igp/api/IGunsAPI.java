package us.universalpvp.igp.api;

import us.universalpvp.igp.manager.GunManager;
import us.universalpvp.igp.manager.PlayerGunRegistry;

/**
 * Created by avigh on 8/25/2016.
 */
public class IGunsAPI {

    private PlayerGunRegistry registry;
    private GunManager manager;
    private static IGunsAPI api;

    public GunManager getGunManager() {
        if (manager == null) {
            manager = new GunManager();
        }
        return manager;
    }

    public PlayerGunRegistry getRegistry() {
        if (registry == null) {
            registry = new PlayerGunRegistry();
        }
        return registry;
    }

    public static IGunsAPI getAPI() {
        if (api == null) {
            api = new IGunsAPI();
        }
        return api;
    }

}
