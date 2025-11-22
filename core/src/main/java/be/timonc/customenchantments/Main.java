package be.timonc.customenchantments;

import be.timonc.customenchantments.commands.enchant.EnchantCommand;
import be.timonc.customenchantments.commands.reload.ReloadCommand;
import be.timonc.customenchantments.engine.CustomEnchant;
import be.timonc.customenchantments.engine.instructions.InstructionCall;
import be.timonc.customenchantments.engine.instructions.InstructionCleanupListeners;
import be.timonc.customenchantments.engine.instructions.types.data.values.ValueManager;
import be.timonc.customenchantments.events.CustomEvent;
import be.timonc.customenchantments.hooks.CEPlaceholderExpansion;
import be.timonc.customenchantments.nms.EnchantmentManager;
import be.timonc.customenchantments.util.Util;
import be.timonc.customenchantments.util.enums.File;
import be.timonc.customenchantments.websocket.WebSocketConnection;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Main extends JavaPlugin {


    private static final Map<String, String> minecraftVersionMapper = Map.of(
            "1.21", "1.21.1",
            "1.21.7", "1.21.8"
    );
    private static final ValueManager valueManager = new ValueManager();
    private static Main plugin;
    private static boolean isPAPISupport;
    private static EnchantmentManager enchantmentsManager;
    private static WebSocketConnection webSocketConnection;
    private static String minecraftVersion;

    public static Main getMain() {
        return plugin;
    }

    public static ValueManager getValueManager() {
        return valueManager;
    }


    public static boolean isPAPISupport() {
        return isPAPISupport;
    }

    public static boolean isFirstBoot() {
        return System.getProperty("RELOAD") == null;
    }

    public static EnchantmentManager getEnchantmentsManager() {
        return enchantmentsManager;
    }

    public static WebSocketConnection getWebSocketConnection() {
        if (webSocketConnection == null)
            webSocketConnection = new WebSocketConnection();
        return webSocketConnection;
    }

    public static void resetWebSocketConnection() {
        webSocketConnection = null;
    }

    private static void shutdownWebSocketConnection() {
        if (webSocketConnection != null)
            webSocketConnection.shutdown();
    }

    @Override
    public void onEnable() {
        plugin = this;
        if (!isFirstBoot())
            Util.warn("Reloading will not load all changes made to an enchantment. Restart to apply changes properly!");

        // Register and load the files
        File.register();

        checkPAPISupport();

        createNMSClasses();
        if (enchantmentsManager == null) {
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        valueManager.loadPersistentValues();

        CustomEnchant.register();
        CustomEvent.register();

        registerListeners();
        registerCommands();

        // Creating the WebSocketConnection
        webSocketConnection = new WebSocketConnection();
    }

    @Override
    public void onDisable() {
        System.setProperty("RELOAD", "TRUE");
        valueManager.storePersistentValues();
        shutdownWebSocketConnection();
        InstructionCall.callCleanupCommands();
    }

    private void registerListeners() {
        Util.registerListener(new InstructionCleanupListeners());
    }


    private void checkPAPISupport() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            isPAPISupport = true;
            new CEPlaceholderExpansion();
        } else Util.warn("PlaceholderAPI is not installed. PAPI placeholders will not be replaced.");
    }


    private void registerCommands() {
        new EnchantCommand();
        new ReloadCommand();
    }

    private String getMinecraftVersion() {
        if (minecraftVersion == null) {
            String bukkitGetVersionOutput = Bukkit.getVersion();
            Matcher matcher = Pattern.compile("\\(MC: (?<version>[\\d]+\\.[\\d]+(\\.[\\d]+)?)\\)")
                                     .matcher(bukkitGetVersionOutput);
            if (matcher.find()) {
                minecraftVersion = matcher.group("version");
                minecraftVersion = minecraftVersionMapper.getOrDefault(minecraftVersion, minecraftVersion);
            } else
                throw new RuntimeException("Could not determine Minecraft version from Bukkit.getVersion(): " + bukkitGetVersionOutput);
        }

        return minecraftVersion;
    }

    @SuppressWarnings("unchecked")
    private void createNMSClasses() throws RuntimeException {
        String baseClazzName = "be.timonc.customenchantments.nms_" + getMinecraftVersion()
                .replace(".", "_") + ".";

        try {
            Class<? extends EnchantmentManager> enchantmentManagerClass = (Class<? extends EnchantmentManager>) Class.forName(
                    baseClazzName + "EnchantmentManagerImpl");

            enchantmentsManager = enchantmentManagerClass.getConstructor().newInstance();
        } catch (ClassNotFoundException exception) {
            Util.error("Minecraft " + getMinecraftVersion() +
                    " is not supported by this version of CustomEnchantments");
            Util.error("Download our latest update for newer versions!");
        } catch (ReflectiveOperationException ignored) {
            Util.error(ignored.getMessage());
        }
    }
}
