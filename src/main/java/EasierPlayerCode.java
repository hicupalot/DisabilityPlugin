import java.util.HashSet;
import java.util.UUID;

public class EasierPlayerCode {
    // used to reduce overhead for packet listeners
    private final HashSet<UUID> hearingImpairedPlayers = new HashSet<>();

    public void addPlayer(UUID uuid) {
        hearingImpairedPlayers.add(uuid);
    }

    public void removePlayer(UUID uuid) {
        hearingImpairedPlayers.remove(uuid);
    }

    public boolean isHearingSet(UUID uuid) {
        return hearingImpairedPlayers.contains(uuid);

    }
}
