package be.timonc.customenchantments.enchantments.defaultenchants.handlers;

import be.timonc.customenchantments.enchantments.defaultenchants.DefaultCustomEnchant;
import be.timonc.customenchantments.enchantments.defaultenchants.DefaultTriggerListener;
import be.timonc.customenchantments.other.Util;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Excavator extends DefaultTriggerListener {

    private final Set<Player> antiRecursion = new HashSet<>();

    private final Map<Tag<Material>, Tag<Material>> toolToBlockTags = Map.of(
            Tag.ITEMS_AXES, Tag.MINEABLE_AXE,
            Tag.ITEMS_HOES, Tag.MINEABLE_HOE,
            Tag.ITEMS_SHOVELS, Tag.MINEABLE_SHOVEL,
            Tag.ITEMS_PICKAXES, Tag.MINEABLE_PICKAXE
    );

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        Player player = e.getPlayer();
        DefaultCustomEnchant defaultCustomEnchant = DefaultCustomEnchant.EXCAVATOR;

        if (antiRecursion.contains(player)) return;
        if (!defaultCustomEnchant.check(player)) return;

        ItemStack enchantedItem = Util.getEnchantedItem(player, defaultCustomEnchant.get());
        if (enchantedItem == null) return;
        Block centerBlock = e.getBlock();
        Vector direction = player.getLocation().getDirection();
        int enchantedLevel = enchantedItem.getEnchantmentLevel(defaultCustomEnchant.get().getEnchantment());

        if (!isCorrectTool(enchantedItem, centerBlock))
            return;

        antiRecursion.add(player);

        for (int i = -enchantedLevel; i <= enchantedLevel; i++) {
            for (int j = -enchantedLevel; j <= enchantedLevel; j++) {
                Block adjacentBlock;
                if (Math.abs(direction.getY()) > 0.5)
                    adjacentBlock = centerBlock.getRelative(i, 0, j);
                else if (Math.abs(direction.getX()) > Math.abs(direction.getZ()))
                    adjacentBlock = centerBlock.getRelative(0, i, j);
                else
                    adjacentBlock = centerBlock.getRelative(i, j, 0);

                if (isCorrectTool(enchantedItem, adjacentBlock))
                    player.breakBlock(adjacentBlock);
            }
        }

        antiRecursion.remove(player);
    }


    private boolean isCorrectTool(ItemStack tool, Block block) {
        return toolToBlockTags.entrySet()
                              .stream()
                              .anyMatch(entry -> entry.getKey().isTagged(tool.getType()) && entry.getValue()
                                                                                                 .isTagged(block.getType()));
    }
}
