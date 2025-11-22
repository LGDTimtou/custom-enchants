package be.timonc.customenchantments.engine.triggers.impl.health_change;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroupType;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Map;
import java.util.Set;

public class TakeDamageTrigger extends TriggerListener {

    private final TriggerConditionGroup newHealthConditions = new TriggerConditionGroup(
            "new_health", TriggerConditionGroupType.NUMBER
    );
    private final TriggerConditionGroup previousHealthConditions = new TriggerConditionGroup(
            "previous_health", TriggerConditionGroupType.NUMBER
    );
    private final TriggerConditionGroup damageAmountConditions = new TriggerConditionGroup(
            "damage", TriggerConditionGroupType.NUMBER
    );
    private final TriggerConditionGroup damageCauseConditions = new TriggerConditionGroup(
            "damage", TriggerConditionGroupType.CAUSE
    );

    public TakeDamageTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        triggerInvoker.trigger(
                event,
                player,
                Map.of(
                        newHealthConditions, player.getHealth() - event.getFinalDamage(),
                        previousHealthConditions, player.getHealth(),
                        damageAmountConditions, event.getFinalDamage(),
                        damageCauseConditions, event.getCause()
                ),
                Map.of()
        );
    }


    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of(newHealthConditions, previousHealthConditions, damageAmountConditions, damageCauseConditions);
    }
}
