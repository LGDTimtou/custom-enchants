package be.timonc.customenchantments.engine.triggers.impl.click;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroupType;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Map;
import java.util.Set;

public class ShiftRightClickEntityTrigger extends TriggerListener {

    private final TriggerConditionGroup clickedEntityConditions = new TriggerConditionGroup(
            "clicked", TriggerConditionGroupType.ENTITY
    );

    public ShiftRightClickEntityTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }


    @EventHandler
    public void onPlayerRightClickEntity(PlayerInteractEntityEvent event) {
        if (!event.getPlayer().isSneaking()) return;

        triggerInvoker.trigger(event, event.getPlayer(), Map.of(clickedEntityConditions, event.getRightClicked()));
    }

    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of(clickedEntityConditions);
    }
}
