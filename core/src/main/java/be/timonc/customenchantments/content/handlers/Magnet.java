package be.timonc.customenchantments.content.handlers;

import be.timonc.customenchantments.content.DefaultCustomEnchant;
import be.timonc.customenchantments.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Magnet extends BukkitRunnable {

    private static final double pullStrength = 2;

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ItemStack enchantedItem = Util.getEnchantedItem(player, DefaultCustomEnchant.MAGNET.get());
            if (enchantedItem == null) continue;

            Location location = player.getLocation();
            if (location.getWorld() == null) continue;

            Vector locationVector = location.add(0, 1, 0).toVector();

            int level = enchantedItem.getEnchantmentLevel(DefaultCustomEnchant.MAGNET.get().getEnchantment());
            for (Entity entity : location.getWorld().getNearbyEntities(location, level, level, level)) {
                if (!(entity instanceof Item item) || item.getPickupDelay() > 0) continue;

                Vector pullVector = locationVector.subtract(item.getLocation().toVector());

                pullVector.normalize().multiply(pullStrength);
                item.setVelocity(pullVector);
            }
        }
    }
}
