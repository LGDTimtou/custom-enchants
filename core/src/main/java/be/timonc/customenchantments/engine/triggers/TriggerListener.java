package be.timonc.customenchantments.engine.triggers;

import be.timonc.customenchantments.engine.triggers.conditions.GlobalTriggerCondition;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerCondition;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import org.bukkit.event.Listener;

import java.util.Set;

public abstract class TriggerListener implements Listener {

    protected TriggerInvoker triggerInvoker;
    protected Set<TriggerCondition> conditions;

    public TriggerListener(TriggerInvoker triggerInvoker) {
        this.triggerInvoker = triggerInvoker;
    }

    public Set<TriggerCondition> getConditions() {
        if (conditions == null) {
            Set<TriggerConditionGroup> groups = getConditionGroups();
            this.conditions = TriggerConditionGroup.getFlatTriggerConditions(groups);
            this.conditions.addAll(TriggerConditionGroup.getFlatTriggerConditions(GlobalTriggerCondition.get()));
        }
        return conditions;
    }

    protected abstract Set<TriggerConditionGroup> getConditionGroups();
}
