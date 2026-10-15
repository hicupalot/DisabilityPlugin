import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class IBS implements Listener {
    @EventHandler
    private void onPlayerItemConsume(PlayerItemConsumeEvent e) {
        FileConfiguration config = DisabilityPlugin.getInstance().getConfig();
        Player player = e.getPlayer();
        Location legs = player.getEyeLocation().subtract(0, 1, 0);
        World world = player.getWorld();
        if (config.getStringList("ibs").contains(player.getUniqueId().toString())) {
            Item poop = (Item) world.spawnEntity(legs, EntityType.ITEM);
            poop.setItemStack(new ItemStack(Material.BROWN_CONCRETE));
            poop.setCanPlayerPickup(false);
            poop.setCanMobPickup(false);

            new BukkitRunnable() {
                //------------------------------------------------------------------------------//
                @Override
                public void run() {
                    if (poop.isValid()) {
                        poop.remove();
                    }
                }
            }.runTaskLater(DisabilityPlugin.getInstance(), 100);
        }
    }
}
