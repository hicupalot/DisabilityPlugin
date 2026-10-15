import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public class HardofHearing extends PacketAdapter {
    FileConfiguration config = DisabilityPlugin.getInstance().getConfig();
    private final EasierPlayerCode data;

    public HardofHearing(JavaPlugin plugin, EasierPlayerCode data) {
        super(plugin, ListenerPriority.NORMAL,
                PacketType.Play.Server.ENTITY_SOUND,
                PacketType.Play.Server.NAMED_SOUND_EFFECT
        );
        this.data = data;
    }

    @Override
    public void onPacketSending(PacketEvent e) {
        PacketContainer packet = e.getPacket();
        Player player = e.getPlayer();
        if (data.isHearingSet(player.getUniqueId())){
            if (packet.getFloat().size() > 0) {
                float currentVolume = packet.getFloat().read(0);
                float lowerVolume = currentVolume * 0.1f;
                packet.getFloat().write(0, lowerVolume);
            }
        }
    }
}

