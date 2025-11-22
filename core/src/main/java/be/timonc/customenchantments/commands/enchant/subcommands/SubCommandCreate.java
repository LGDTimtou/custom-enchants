package be.timonc.customenchantments.commands.enchant.subcommands;

import be.timonc.customenchantments.Main;
import be.timonc.customenchantments.commands.Command;
import be.timonc.customenchantments.commands.SubCommand;
import be.timonc.customenchantments.util.enums.Message;
import org.bukkit.command.CommandSender;

import java.util.List;

public class SubCommandCreate extends SubCommand {


    public SubCommandCreate(Command command) {
        super(command, "create", 0, Message.COMMANDS__CREATE__USAGE.get());
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        Main.getWebSocketConnection().sendEnchantment(commandSender, null);
    }

    @Override
    public List<String> getTabValues(CommandSender commandSender, String[] args) {
        return List.of();
    }
}
