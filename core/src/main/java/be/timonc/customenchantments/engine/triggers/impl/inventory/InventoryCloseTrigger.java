package be.timonc.customenchantments.engine.triggers.impl.inventory;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroupType;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Map;
import java.util.Set;

public class InventoryCloseTrigger extends TriggerListener {

    private final TriggerConditionGroup topInventoryConditions = new TriggerConditionGroup(
            "top", TriggerConditionGroupType.INVENTORY
    );
    private final TriggerConditionGroup bottomInventoryConditions = new TriggerConditionGroup(
            "bottom", TriggerConditionGroupType.INVENTORY
    );
    private final TriggerConditionGroup titleConditions = new TriggerConditionGroup(
            "inventory_title", TriggerConditionGroupType.STRING
    );

    public InventoryCloseTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }


    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        triggerInvoker.trigger(
                e, (Player) e.getPlayer(), Map.of(
                        topInventoryConditions, e.getView().getTopInventory(),
                        bottomInventoryConditions, e.getView().getBottomInventory(),
                        titleConditions, e.getView().getTitle()
                )
        );
    }

    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of(topInventoryConditions, bottomInventoryConditions, titleConditions);
    }
}
