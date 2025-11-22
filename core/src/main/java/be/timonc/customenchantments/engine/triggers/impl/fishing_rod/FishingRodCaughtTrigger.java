package be.timonc.customenchantments.engine.triggers.impl.fishing_rod;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroupType;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.Map;
import java.util.Set;

public class FishingRodCaughtTrigger extends TriggerListener {

    private final TriggerConditionGroup caughtItemConditions = new TriggerConditionGroup(
            "caught", TriggerConditionGroupType.ITEM
    );

    public FishingRodCaughtTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }


    @EventHandler
    public void onFish(PlayerFishEvent e) {
        if (e.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;
        if (e.getCaught() == null) return;

        triggerInvoker.trigger(
                e,
                e.getPlayer(),
                Map.of(
                        caughtItemConditions,
                        ((Item) e.getCaught()).getItemStack()
                )
        );
    }

    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of(caughtItemConditions);
    }
}
