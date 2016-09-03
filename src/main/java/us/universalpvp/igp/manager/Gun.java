package us.universalpvp.igp.manager;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.universalpvp.igp.clip.Clip;

/**
 * Created by avigh on 8/11/2016.
 */
public abstract class Gun {

    private final String name;
    private final GunType type;
    private final Clip clip;

    public Gun(String name, GunType type, Clip clip) {
        this.name = name;
        this.type = type;
        this.clip = clip;
    }

    public String getName() {
        return name;
    }

    public GunType getType() {
        return type;
    }

    public Clip getClip() {
        return clip;
    }

    public abstract ItemStack getBukkitItemStack();

    public abstract double getReloadTime();

    public abstract float getRecoil();

    public abstract String getPermission();

    public abstract String noPermission();

    public abstract double getMagnitude();

    public abstract long getRateOfFire();

    public abstract double getVelocityMultiplier();

    public abstract void onInteract(PlayerInteractEvent e);

    public abstract void onHit(EntityDamageByEntityEvent e);

    public abstract void onBlockHit(ProjectileHitEvent e);
}

