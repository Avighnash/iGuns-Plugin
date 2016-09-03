package us.universalpvp.igp.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import us.universalpvp.igp.manager.Gun;

/**
 * Created by avigh on 8/13/2016.
 */
public class GunEquipEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player holder;
    private final Gun gun;

    public GunEquipEvent(Player holder, Gun gun) {
        this.holder = holder;
        this.gun = gun;
    }

    public Gun getGun() {
        return gun;
    }

    public Player getHolder() {
        return holder;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
