package net.mario.lepuscommunemod.commune;

import net.minecraft.core.BlockPos;
import java.util.*;

/**
 * In‐memory data for a single “commune” (a base/outpost).
 */
public class CommuneData {
    // Unique identifier for this commune
    private final UUID id = UUID.randomUUID();

    // Where the base is located in the world
    private final BlockPos center;

    // Which bunnies belong to this commune (by their UUID)
    private final Set<UUID> members = new HashSet<>();

    // Shared resource counts (e.g. "carrot" → 5, "wood" → 2)
    private final Map<String, Integer> resources = new HashMap<>();

    // The one leader bunny’s UUID (null if no leader currently)
    private UUID leaderUUID;

    /**
     * Constructor.
     *
     * @param center      The BlockPos where this commune is centered.
     * @param leaderUUID  The UUID of the first leader bunny.
     */
    public CommuneData(BlockPos center, UUID leaderUUID) {
        this.center = center;
        this.leaderUUID = leaderUUID;

        // Mark the leader as the first member
        this.members.add(leaderUUID);

        // Initialize resource counts to zero so getResource(...) won’t NPE
        this.resources.put("carrot", 0);
        this.resources.put("wood", 0);
        this.resources.put("dirt", 0);
    }

    // ─── Getters / Setters ──────────────────────────────────

    /** Returns this commune’s unique ID. */
    public UUID getId() {
        return id;
    }

    /** Returns the center position of the base. */
    public BlockPos getCenter() {
        return center;
    }

    /** Returns the current leader’s UUID (or null if none). */
    public UUID getLeaderUUID() {
        return leaderUUID;
    }

    /** Sets a new leader’s UUID. */
    public void setLeaderUUID(UUID newLeader) {
        this.leaderUUID = newLeader;
    }

    // ─── Member Management ──────────────────────────────────

    /** Adds a bunny’s UUID to this commune’s member set. */
    public void addMember(UUID bunnyId) {
        members.add(bunnyId);
    }

    /**
     * Removes a bunny’s UUID. If that bunny was the leader, clears leaderUUID.
     */
    public void removeMember(UUID bunnyId) {
        members.remove(bunnyId);
        if (bunnyId.equals(leaderUUID)) {
            // Leader died or left → no current leader
            leaderUUID = null;
        }
    }

    /** Returns true if the given bunny UUID is in this commune. */
    public boolean isMember(UUID bunnyId) {
        return members.contains(bunnyId);
    }

    /** Returns an unmodifiable view of all member UUIDs. */
    public Set<UUID> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    // ─── Resource Management ────────────────────────────────

    /**
     * Adds `amount` of `type` (e.g., "carrot") to this commune’s stock.
     */
    public void addResource(String type, int amount) {
        resources.put(type, resources.getOrDefault(type, 0) + amount);
    }

    /**
     * Attempts to consume `amount` of `type`.
     * Returns true if successful, false if not enough.
     */
    public boolean consumeResource(String type, int amount) {
        int have = resources.getOrDefault(type, 0);
        if (have < amount) return false;
        resources.put(type, have - amount);
        return true;
    }

    /**
     * Returns how many of `type` this commune currently has.
     * (If not present, returns 0.)
     */
    public int getResource(String type) {
        return resources.getOrDefault(type, 0);
    }
}

