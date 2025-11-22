package be.timonc.customenchantments.engine.triggers.impl.block;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroupType;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Map;
import java.util.Set;

public class BlockBreakTrigger extends TriggerListener {

    private final TriggerConditionGroup brokenBlockConditions = new TriggerConditionGroup(
            "broken", TriggerConditionGroupType.BLOCK
    );

    public BlockBreakTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        triggerInvoker.trigger(
                e,
                e.getPlayer(),
                Map.of(brokenBlockConditions, e.getBlock())
        );
    }

    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of(brokenBlockConditions);
    }
}
