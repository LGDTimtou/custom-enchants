package be.timonc.customenchantments.hooks;

import be.timonc.customenchantments.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CEPlaceholderExpansion extends PlaceholderExpansion {

    public CEPlaceholderExpansion() {
        this.register();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "ce";
    }

    @Override
    public @NotNull String getAuthor() {
        return String.join(", ", Main.getMain().getDescription().getAuthors());
    }

    @Override
    public @NotNull String getVersion() {
        return Main.getMain().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        return Main.getValueManager().getValue(params);
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        return onRequest(player, params);
    }
}
