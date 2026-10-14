import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Amputation implements Listener {
@EventHandler
    public void onPlayerTrytoWalk(PlayerMoveEvent e){
    Player player = e.getPlayer();
    FileConfiguration config = DisabilityPlugin.getInstance().getConfig();
    if (config.getList("amputated").contains(player.getUniqueId().toString())) {
        if (!player.isJumping()){
            e.setCancelled(true);
        }
    }
}
}
