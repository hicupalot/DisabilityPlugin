import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisabilityPlugin extends JavaPlugin {
    private static DisabilityPlugin instance;
    public static DisabilityPlugin getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        String startup = ChatColor.translateAlternateColorCodes('&',"&6[DISABILITYPLUGIN] is starting");
        getLogger().info(startup);
        instance = this;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {

        saveConfig();

    }
}
