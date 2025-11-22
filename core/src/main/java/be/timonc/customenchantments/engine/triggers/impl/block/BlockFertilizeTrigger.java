package be.timonc.customenchantments.engine.triggers.impl.block;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroupType;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockFertilizeEvent;

import java.util.Map;
import java.util.Set;

public class BlockFertilizeTrigger extends TriggerListener {

    private final TriggerConditionGroup fertilizedBlockConditions = new TriggerConditionGroup(
            "fertilized", TriggerConditionGroupType.BLOCK
    );

    public BlockFertilizeTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }


    @EventHandler
    public void onFertilize(BlockFertilizeEvent e) {
        triggerInvoker.trigger(
                e,
                e.getPlayer(),
                Map.of(fertilizedBlockConditions, e.getBlock())
        );
    }

    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of(fertilizedBlockConditions);
    }
}
