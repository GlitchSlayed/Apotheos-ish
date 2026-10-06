package com.summit.spawner;

import net.minecraft.world.entity.Mob;
import java.util.Map;
import java.util.WeakHashMap;

public final class DespawnGrace {
    private static final Map<Mob, Integer> GRACE_TICKS = new WeakHashMap<>();

    public static void apply(Mob mob, int ticks) {
        if (mob != null) {
            GRACE_TICKS.put(mob, ticks);
        }
    }

    public static boolean hasGrace(Mob mob) {
        if (mob == null) return false;
        Integer ticks = GRACE_TICKS.get(mob);
        if (ticks == null) return false;
        int remaining = ticks - 1;
        if (remaining <= 0) {
            GRACE_TICKS.remove(mob);
            return false;
        }
        GRACE_TICKS.put(mob, remaining);
        return true;
    }

    private DespawnGrace() {}
}
