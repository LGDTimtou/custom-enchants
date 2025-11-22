package be.timonc.customenchantments.engine.triggers.impl.block_other;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BellRingEvent;

import java.util.Set;

public class BellRungTrigger extends TriggerListener {


    public BellRungTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }

    @EventHandler
    public void onBellRing(BellRingEvent e) {
        if (e.getEntity() == null || !(e.getEntity() instanceof Player player)) return;
        triggerInvoker.trigger(e, player);
    }

    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of();
    }
}
