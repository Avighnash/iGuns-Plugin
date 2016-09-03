package us.universalpvp.igp.guns;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.universalpvp.igp.clip.Clip;
import us.universalpvp.igp.manager.Gun;
import us.universalpvp.igp.manager.GunShootingHelper;
import us.universalpvp.igp.manager.GunType;


/**
 * Created by avigh on 8/11/2016.
 */
public class AK_47 extends Gun {

    /**
     * Constructor
     */
    public AK_47() {
        super("AK-47", GunType.ASUALT_RIFLE, new Clip(null, 0, 0));
    }

    /**
     * @return An itemstack, specifically, the itemstack for the gun.
     */
    @Override
    public ItemStack getBukkitItemStack() {
        ItemStack itemStack = new ItemStack(Material.ACACIA_DOOR);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("poop");

        return itemStack;
    }

    /**
     * @return Reload time
     */
    @Override
    public double getReloadTime() {
        return 10;
    }

    /**
     * @return Recoil amount
     */
    @Override
    public float getRecoil() {
        return 5;
    }

    /**
     * @return Permission to use this gun
     */
    @Override
    public String getPermission() {
        return "Bleh.nle";
    }

    /**
     * @return No permission string
     */
    @Override
    public String noPermission() {
        return null;
    }

    /**
     * @return Amount of damage it would cause to a block if hit (GunBlockHitEvent)
     */
    @Override
    public double getMagnitude() {
        return 1;
    }

    /**
     * @return Rate of Fire
     */
    @Override
    public long getRateOfFire() {
        return 0;
    }

    /**
     * @return Velocity of the bullet
     */
    @Override
    public double getVelocityMultiplier() {
        return 0.1;
    }

    /**
     * @param e {@link PlayerInteractEvent}
     * @return Handles shooting
     */
    @Override
    public void onInteract(PlayerInteractEvent e) {
        GunShootingHelper helper = GunShootingHelper.getHelper(this);

        Snowball snowball = (Snowball) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.SNOWBALL);

        //manages ROF, calls the shoot event, applies recoil, sets metadata values, and reloads gun if needed
        helper.shoot(e.getPlayer(), snowball);

        /*
            //reload gun
            helper.reload(e.getPlayer());

            //manage the rate of fire
            helper.manageROF();

            //call the GunShootEvent
            helper.callShootingEvent(-stuff-);

            //apply recoil
            helper.recoil(snowball);

            //set metadata values
            helper.setMetadataValues(snowball);
        */
    }

    /**
     * @param e {@link EntityDamageByEntityEvent}
     */
    @Override
    public void onHit(EntityDamageByEntityEvent e) {
        e.setDamage(2000000);
    }

    /**
     * @param e {@link ProjectileHitEvent}
     */
    @Override
    public void onBlockHit(ProjectileHitEvent e) {
        Bukkit.broadcastMessage("oh meh geese... it hit a blok!");
    }
}
