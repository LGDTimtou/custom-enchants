package be.timonc.customenchantments.command.reload;

import be.timonc.customenchantments.command.Command;
import be.timonc.customenchantments.other.Message;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand extends Command {

    private static final String plugmanXURL = "https://modrinth.com/plugin/plugmanx";

    public ReloadCommand() {
        super("reload");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] strings) {
        if (!hasPermission(commandSender)) {
            commandSender.sendMessage(Message.COMMANDS__NO_PERMISSION.get());
            return false;
        }

        commandSender.sendMessage(Message.COMMANDS__RELOAD__DISABLED.get());

        TextComponent reloadAlternativeText = new TextComponent(Message.COMMANDS__RELOAD__ALTERNATIVE.get());

        TextComponent plugManXText = new TextComponent(Message.COMMANDS__RELOAD__PLUGMAN_X.getNoPrefix());
        plugManXText.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, plugmanXURL));
        plugManXText.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new Text(Message.COMMANDS__RELOAD__PLUGMAN_X_HOVER.getNoPrefix())
        ));
        reloadAlternativeText.addExtra(plugManXText);

        commandSender.spigot().sendMessage(reloadAlternativeText);

        return true;
    }
}
