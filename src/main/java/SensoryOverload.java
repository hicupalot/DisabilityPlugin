import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class SensoryOverload implements Listener {
    @EventHandler
    public void explosion(EntityExplodeEvent e) {
        double initialVolume = 0;
        double R1 = 0;
        Location explosion = e.getLocation();
        FileConfiguration config = DisabilityPlugin.getInstance().getConfig();
        Collection<Player> player = e.getLocation().getNearbyPlayers(500);
        Entity exploder = e.getEntity();
        if (exploder instanceof Creeper creeper) {
            if (creeper.isPowered()){
                initialVolume = 176; // decibels
                R1 = 6; // explosion radius
            }
            else {
                initialVolume = 170; //decibels
                R1 = 3; //exposion radius
            }

        }
        else if (exploder instanceof TNTPrimed){
            initialVolume = 176; //decibels
            R1 =4; //explosion radius
        }
        else if (exploder instanceof LargeFireball){
            initialVolume = 160;
            R1 = 1;
        }
        else if (exploder instanceof Wither){
            initialVolume = 185;
            R1 = 7;
        }
        else if (exploder instanceof WitherSkull){
            initialVolume = 170;
            R1=1;
        }
        else if (exploder instanceof EnderCrystal){
            initialVolume = 180;
            R1=6;

        }
        else {
            initialVolume = 150;
            R1 = 2;
        }
        for (Player p : player) {
            if (config.getList("sensoryOverload").contains(p.getUniqueId().toString())) {
                Location playerLocation = p.getLocation();
                double R2 = explosion.distance(playerLocation);
                if (R2 < 0.1){
                    R2 = 0.1;
                }
                double finalVolume = initialVolume - 20 * Math.log10(R2 / R1);
                if (finalVolume >= 140) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 1200, 1));
                } else if (finalVolume >= 120) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 600, 1));
                } else if (finalVolume >= 100) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 300, 1));
                } else if (finalVolume >= 80) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 200, 1));
                }
                if (p.hasPermission("disabilityplugin.debug")){
                    p.sendMessage("Detected Sound at " +finalVolume + "db");
                }
            }
        }
    }
}
