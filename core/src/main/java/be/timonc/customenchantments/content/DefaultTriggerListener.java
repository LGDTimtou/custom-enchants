package be.timonc.customenchantments.content;

import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.TriggerListener;

import java.util.Set;

public class DefaultTriggerListener extends TriggerListener {


    protected DefaultTriggerListener() {
        super(null);
    }

    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of();
    }
}
