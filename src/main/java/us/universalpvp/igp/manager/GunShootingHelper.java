package us.universalpvp.igp.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import us.universalpvp.igp.clip.Clip;
import us.universalpvp.igp.events.GunShootEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by avigh on 8/25/2016.
 */
public class GunShootingHelper {

    private Gun gun;
    private int ammo;

    private static final Map<Gun, GunShootingHelper> map = new HashMap<>();
    private final Map<ItemStack, Long> cooldown = new HashMap<>();

    public GunShootingHelper(Gun gun) {
        this.gun = gun;
        ammo = gun.getClip().getSize();
    }

    public void shoot(Player player, Projectile projectile) {
        if (player.getInventory().getItemInMainHand().isSimilar(gun.getBukkitItemStack())) {

            if (ammo > 0) {
                projectile.setMetadata(gun.getName(), new FixedMetadataValue(Bukkit.getPluginManager()
                        .getPlugin("IGunsMain"), gun.getName()));
                projectile.getLocation().setPitch(projectile.getLocation().getPitch() + gun.getRecoil());

                projectile.setVelocity(projectile.getVelocity().multiply(gun.getVelocityMultiplier()));
                projectile.setShooter(player);

                long rof = gun.getRateOfFire();

                if (System.currentTimeMillis() - cooldown
                        .getOrDefault(gun.getBukkitItemStack(), rof) < rof)
                    return;
                cooldown.put(gun.getBukkitItemStack(), System.currentTimeMillis());

                ammo--;
            } else
                reload(player);
        }
    }

    public void reload(Player player) {
        Clip clip = gun.getClip();

        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack.isSimilar(clip.getBukkitItemStack())) {
                itemStack.setAmount(itemStack.getAmount() - 1);
            }
        }

    }


    public Map<ItemStack, Long> getCooldown() {
        return cooldown;
    }

    public static GunShootingHelper getHelper(Gun gun) {
        if (!map.containsKey(gun)) {
            GunShootingHelper shootingHelper = new GunShootingHelper(gun);
            map.put(gun, shootingHelper);
        }
        return map.get(gun);
    }

    public void callShootingEvent(Player p, Projectile projectile) {
        Bukkit.getPluginManager().callEvent(new GunShootEvent(p, gun, projectile));
    }

    public void setMetadataValues(Projectile projectile) {
        projectile.setMetadata(gun.getName(), new FixedMetadataValue(Bukkit.getPluginManager()
                .getPlugin("IGunsMain"), gun.getName()));
    }

    public float recoil(Projectile projectile) {
        projectile.getLocation().setPitch(projectile.getLocation().getPitch() + gun.getRecoil());

        return projectile.getLocation().getPitch();
    }

    public void manageROF() {
        long rof = gun.getRateOfFire();

        if (System.currentTimeMillis() - cooldown
                .getOrDefault(gun.getBukkitItemStack(), rof) < rof)
            return;
        cooldown.put(gun.getBukkitItemStack(), System.currentTimeMillis());
    }

}
