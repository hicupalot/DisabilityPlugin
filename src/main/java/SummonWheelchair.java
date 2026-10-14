import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class SummonWheelchair implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            // Remember to setup message.yml
            return false;
        }
        Location location = player.getLocation();
        World world = player.getWorld();
        Pig wheelchair = (Pig) world.spawnEntity(location, EntityType.PIG);
        wheelchair.setInvulnerable(true);
        wheelchair.setSaddle(true);
        wheelchair.setPersistent(true);
        if (wheelchair.getAttribute(Attribute.STEP_HEIGHT) != null) {
            wheelchair.getAttribute(Attribute.STEP_HEIGHT).setBaseValue(0.51); //stops it going upstairs

            NamespacedKey key = new NamespacedKey((Plugin) this, "wheelchair");
            wheelchair.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);
            wheelchair.addPassenger(player);
        }
    return true;
    }
}