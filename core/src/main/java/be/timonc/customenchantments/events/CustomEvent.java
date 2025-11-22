package be.timonc.customenchantments.events;

import be.timonc.customenchantments.events.armor_equip.ArmorListener;
import be.timonc.customenchantments.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import java.util.Collections;

public class CustomEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    public CustomEvent(Player who) {
        super(who);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public static void register() {
        Util.registerListener(new ArmorListener(Collections.emptyList()));
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
