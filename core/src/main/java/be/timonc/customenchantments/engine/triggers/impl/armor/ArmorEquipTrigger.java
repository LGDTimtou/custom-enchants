package be.timonc.customenchantments.engine.triggers.impl.armor;

import be.timonc.customenchantments.engine.triggers.TriggerInvoker;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroup;
import be.timonc.customenchantments.engine.triggers.conditions.TriggerConditionGroupType;
import be.timonc.customenchantments.engine.triggers.TriggerListener;
import be.timonc.customenchantments.events.armor_equip.ArmorEquipEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;

public class ArmorEquipTrigger extends TriggerListener {

    private final TriggerConditionGroup newArmorConditions = new TriggerConditionGroup(
            "new_armor", TriggerConditionGroupType.ITEM
    );
    private final TriggerConditionGroup oldArmorConditions = new TriggerConditionGroup(
            "old_armor", TriggerConditionGroupType.ITEM
    );

    public ArmorEquipTrigger(TriggerInvoker triggerInvoker) {
        super(triggerInvoker);
    }


    @EventHandler
    public void onArmorEquip(ArmorEquipEvent e) {
        if (e.getNewArmorPiece() == null) return;
        triggerInvoker.trigger(
                e,
                e.getPlayer(),
                Set.of(e.getNewArmorPiece()),
                Map.of(
                        newArmorConditions,
                        e.getNewArmorPiece(),
                        oldArmorConditions,
                        e.getOldArmorPiece() == null ? new ItemStack(Material.AIR) : e.getOldArmorPiece()
                ),
                Map.of()
        );
    }

    @Override
    protected Set<TriggerConditionGroup> getConditionGroups() {
        return Set.of(newArmorConditions, oldArmorConditions);
    }
}
