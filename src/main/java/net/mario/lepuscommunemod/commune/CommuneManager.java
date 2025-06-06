package net.mario.lepuscommunemod.commune;

import net.minecraft.core.BlockPos;
import java.util.*;

public class CommuneManager {
    private static final Map<UUID, CommuneData> ALL_COMMUNES = new HashMap<>();

    public static void registerCommune(CommuneData comm) {
        ALL_COMMUNES.put(comm.getId(), comm);
    }
    public static void removeCommune(UUID id) {
        ALL_COMMUNES.remove(id);
    }
    public static CommuneData getCommune(UUID id) {
        return ALL_COMMUNES.get(id);
    }

    public static Collection<CommuneData> getAllCommunes() {
        return Collections.unmodifiableCollection(ALL_COMMUNES.values());
    }

    public static CommuneData findNearestCommune(BlockPos pos, double maxRadius) {
        CommuneData nearest = null;
        double bestDist = maxRadius * maxRadius;
        for (CommuneData c : ALL_COMMUNES.values()) {
            double dist = pos.distSqr(c.getCenter());
            if (dist < bestDist) {
                bestDist = dist;
                nearest = c;
            }
        }
        return nearest;
    }
}