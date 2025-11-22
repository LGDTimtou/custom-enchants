package be.timonc.customenchantments.engine.triggers.impl.movement;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.Set;

public class PlayerFlyStopTrigger extends TriggerListener {

    public PlayerFlyStopTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }

    @EventHandler
    public void onToggleFly(PlayerToggleFlightEvent event) {
        if (event.isFlying()) return;
        triggerInvoker.trigger(event, event.getPlayer());
    }

    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of();
    }
}
