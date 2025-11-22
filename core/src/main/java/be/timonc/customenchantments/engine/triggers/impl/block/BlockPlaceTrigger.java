package be.timonc.customenchantments.engine.triggers.impl.block;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroupType;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Map;
import java.util.Set;

public class BlockPlaceTrigger extends TriggerListener {

    private final TriggerConditionGroup placedBlockConditions = new TriggerConditionGroup(
            "placed", TriggerConditionGroupType.BLOCK
    );
    private final TriggerConditionGroup againstBlockConditions = new TriggerConditionGroup(
            "against", TriggerConditionGroupType.BLOCK
    );

    public BlockPlaceTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }


    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        if (!e.canBuild()) return;
        triggerInvoker.trigger(
                e, e.getPlayer(), Map.of(
                        placedBlockConditions,
                        e.getBlockPlaced(),
                        againstBlockConditions,
                        e.getBlockAgainst()
                )
        );
    }

    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of(placedBlockConditions, againstBlockConditions);
    }
}
