package be.timonc.customenchantments.enchantments.defaultenchants;

import be.timonc.customenchantments.enchantments.CustomEnchant;
import be.timonc.customenchantments.enchantments.custom.triggers.TriggerListener;
import be.timonc.customenchantments.enchantments.defaultenchants.listeners.Excavator;
import be.timonc.customenchantments.enchantments.defaultenchants.listeners.Lumber;
import be.timonc.customenchantments.enchantments.defaultenchants.listeners.Replenish;
import be.timonc.customenchantments.enchantments.defaultenchants.listeners.Telekinesis;
import org.bukkit.permissions.Permissible;

public enum DefaultCustomEnchant {

    //Enchantments
    REPLENISH("replenish", false, 1, new Replenish()),
    TELEKINESIS("telekinesis", false, 1, new Telekinesis()),
    LUMBER("lumber", false, 1, new Lumber()),
    EXCAVATOR("excavator", true, 2, new Excavator());

    private final String namespacedName;
    private final boolean allowsCustomMaxLevel;
    private final int defaultMaxLevel;
    private final TriggerListener listener;

    private CustomEnchant enchantment;


    DefaultCustomEnchant(String namespacedName, boolean allowsCustomMaxLevel, int defaultMaxLevel, TriggerListener listener) {
        this.namespacedName = namespacedName;
        this.allowsCustomMaxLevel = allowsCustomMaxLevel;
        this.defaultMaxLevel = defaultMaxLevel;
        this.listener = listener;
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

    public TriggerListener getListener() {
        return listener;
    }

    public boolean check(Permissible permissible) {
        return get() != null && get().hasPermission(permissible);
    }
}
