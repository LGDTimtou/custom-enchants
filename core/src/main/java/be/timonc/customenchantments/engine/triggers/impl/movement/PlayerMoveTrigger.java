package be.timonc.customenchantments.engine.triggers.impl.movement;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroupType;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Map;
import java.util.Set;

public class PlayerMoveTrigger extends TriggerListener {

    private final TriggerConditionGroup fromXConditions = new TriggerConditionGroup(
            "from_x", TriggerConditionGroupType.NUMBER
    );
    private final TriggerConditionGroup fromYConditions = new TriggerConditionGroup(
            "from_y", TriggerConditionGroupType.NUMBER
    );
    private final TriggerConditionGroup fromZConditions = new TriggerConditionGroup(
            "from_z", TriggerConditionGroupType.NUMBER
    );

    public PlayerMoveTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        triggerInvoker.trigger(
                e, e.getPlayer(), Map.of(
                        fromXConditions, e.getFrom().getX(),
                        fromYConditions, e.getFrom().getY(),
                        fromZConditions, e.getFrom().getZ()
                )
        );
    }

    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of(fromXConditions, fromYConditions, fromZConditions);
    }
}
