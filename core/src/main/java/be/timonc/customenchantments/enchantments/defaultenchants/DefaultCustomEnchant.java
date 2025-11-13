package be.timonc.customenchantments.enchantments.defaultenchants;

import be.timonc.customenchantments.Main;
import be.timonc.customenchantments.enchantments.CustomEnchant;
import be.timonc.customenchantments.enchantments.custom.triggers.TriggerListener;
import be.timonc.customenchantments.enchantments.defaultenchants.handlers.*;
import be.timonc.customenchantments.other.Util;
import org.bukkit.permissions.Permissible;
import org.bukkit.scheduler.BukkitRunnable;

public enum DefaultCustomEnchant {

    //Enchantments
    REPLENISH("replenish", false, 1, new Replenish(), null),
    TELEKINESIS("telekinesis", false, 1, new Telekinesis(), null),
    LUMBER("lumber", false, 1, new Lumber(), null),
    EXCAVATOR("excavator", true, 2, new Excavator(), null),
    MAGNET("magnet", true, 10, null, new Magnet());

    private final String namespacedName;
    private final boolean allowsCustomMaxLevel;
    private final int defaultMaxLevel;
    private final TriggerListener listener;
    private final BukkitRunnable runnable;

    private CustomEnchant enchantment;


    DefaultCustomEnchant(String namespacedName, boolean allowsCustomMaxLevel, int defaultMaxLevel, TriggerListener listener, BukkitRunnable runnable) {
        this.namespacedName = namespacedName;
        this.allowsCustomMaxLevel = allowsCustomMaxLevel;
        this.defaultMaxLevel = defaultMaxLevel;
        this.listener = listener;
        this.runnable = runnable;
    }

    public CustomEnchant get() {
        if (enchantment == null)
            this.enchantment = CustomEnchant.get(this.namespacedName);
        return enchantment;
    }

    public String getNamespacedName() {
        return namespacedName;
    }

    public boolean allowsCustomMaxLevel() {
        return allowsCustomMaxLevel;
    }

    public int getDefaultMaxLevel() {
        return defaultMaxLevel;
    }

    public void registerHandler() {
        if (listener != null)
            Util.registerListener(listener);
        else if (runnable != null)
            runnable.runTaskTimer(Main.getMain(), 0L, 2L);
    }

    public boolean check(Permissible permissible) {
        return get() != null && get().hasPermission(permissible);
    }
}
