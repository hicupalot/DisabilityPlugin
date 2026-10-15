import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public final class DisabilityPlugin extends JavaPlugin {
    private static DisabilityPlugin instance;
    public static DisabilityPlugin getInstance() {
        return instance;
    }
    private EasierPlayerCode data;
    @Override
    public void onEnable() {
        instance = this;
        this.data = new EasierPlayerCode();
        String startup = ChatColor.translateAlternateColorCodes('&',"&6[DISABILITYPLUGIN] is starting");
        getLogger().info(startup);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new SensoryOverload(),this);
        getServer().getPluginManager().registerEvents(new Amputation(),this);
        ProtocolLibrary.getProtocolManager().addPacketListener(new HardofHearing(this, this.data));
        getCommand("SummonWheelchair").setExecutor(new SummonWheelchair());
    }
    public EasierPlayerCode getData() {
        return this.data;
    }

    @Override
    public void onDisable() {

        saveConfig();

    }
}
