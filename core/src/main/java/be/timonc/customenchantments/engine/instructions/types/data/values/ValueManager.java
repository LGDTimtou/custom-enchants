package be.timonc.customenchantments.engine.instructions.types.data.values;

import be.timonc.customenchantments.util.enums.File;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class ValueManager {

    private final Map<String, Supplier<String>> savedValues = new HashMap<>();
    private final Set<String> persistentValues = new HashSet<>();

    public void setValue(String identifier, String value, boolean persistent) {
        savedValues.put(identifier, () -> value);
        if (persistent)
            persistentValues.add(identifier);
    }

    public void setDefaultValue(String identifier, String value) {
        savedValues.putIfAbsent(identifier, () -> value);
    }

    public String getValue(String identifier) {
        return savedValues.getOrDefault(identifier, () -> "%ce_" + identifier + "%").get();
    }

    public Map<String, Supplier<String>> getSavedValues() {
        return savedValues;
    }

    public void loadPersistentValues() {
        ConfigurationSection section = File.DATA.getConfig().getRoot();
        if (section == null) return;

        for (String key : section.getKeys(false))
            setValue(key, section.getString(key), true);
    }


    public void storePersistentValues() {
        FileConfiguration data = File.DATA.getConfig();
        for (String key : persistentValues)
            data.set(key, savedValues.get(key).get());
        File.DATA.save();
    }
}
