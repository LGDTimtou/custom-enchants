package be.timonc.customenchantments.engine.triggers.impl.block;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroupType;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDamageEvent;

import java.util.Map;
import java.util.Set;

public class BlockDamagedTrigger extends TriggerListener {

    private final TriggerConditionGroup damagedBlockConditions = new TriggerConditionGroup(
            "damaged", TriggerConditionGroupType.BLOCK
    );

    public BlockDamagedTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }


    @EventHandler
    public void onBlockDamage(BlockDamageEvent e) {
        triggerInvoker.trigger(
                e,
                e.getPlayer(),
                Map.of(damagedBlockConditions, e.getBlock())
        );
    }

    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of(damagedBlockConditions);
    }
}
