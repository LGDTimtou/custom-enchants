package be.timonc.customenchantments.engine.triggers.impl.movement;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.Set;

public class PlayerSneakUpTrigger extends TriggerListener {


    public PlayerSneakUpTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }

    @EventHandler
    public void onPlayerSneakUp(PlayerToggleSneakEvent event) {
        if (event.isSneaking()) return;

        triggerInvoker.trigger(event, event.getPlayer());
    }


    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of();
    }
}
